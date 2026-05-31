
-- Module 2 - ANSI SQL Using MySQL

-- Create and use database
CREATE DATABASE IF NOT EXISTS event_portal;
USE event_portal;

-- TABLE CREATION

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    city VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM('upcoming', 'completed', 'cancelled'),
    organizer_id INT,
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

CREATE TABLE Sessions (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    title VARCHAR(200) NOT NULL,
    speaker_name VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Registrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    feedback_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

CREATE TABLE Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    resource_type ENUM('pdf', 'image', 'link'),
    resource_url VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);


INSERT INTO Users (full_name, email, city, registration_date) VALUES
('Alice Johnson', 'alice@example.com', 'New York', '2024-12-01'),
('Bob Smith', 'bob@example.com', 'Los Angeles', '2024-12-05'),
('Charlie Lee', 'charlie@example.com', 'Chicago', '2024-12-10'),
('Diana King', 'diana@example.com', 'New York', '2025-01-15'),
('Ethan Hunt', 'ethan@example.com', 'Los Angeles', '2025-02-01');

INSERT INTO Events (title, description, city, start_date, end_date, status, organizer_id) VALUES
('Tech Innovators Meetup', 'A meetup for tech enthusiasts.', 'New York', '2025-06-10 10:00:00', '2025-06-10 16:00:00', 'upcoming', 1),
('AI & ML Conference', 'Conference on AI and ML advancements.', 'Chicago', '2025-05-15 09:00:00', '2025-05-15 17:00:00', 'completed', 3),
('Frontend Development Bootcamp', 'Hands-on training on frontend tech.', 'Los Angeles', '2025-07-01 10:00:00', '2025-07-03 16:00:00', 'upcoming', 2);

INSERT INTO Sessions (event_id, title, speaker_name, start_time, end_time) VALUES
(1, 'Opening Keynote', 'Dr. Tech', '2025-06-10 10:00:00', '2025-06-10 11:00:00'),
(1, 'Future of Web Dev', 'Alice Johnson', '2025-06-10 11:15:00', '2025-06-10 12:30:00'),
(2, 'AI in Healthcare', 'Charlie Lee', '2025-05-15 09:30:00', '2025-05-15 11:00:00'),
(3, 'Intro to HTML5', 'Bob Smith', '2025-07-01 10:00:00', '2025-07-01 12:00:00');

INSERT INTO Registrations (user_id, event_id, registration_date) VALUES
(1, 1, '2025-05-01'),
(2, 1, '2025-05-02'),
(3, 2, '2025-04-30'),
(4, 2, '2025-04-28'),
(5, 3, '2025-06-15');

INSERT INTO Feedback (user_id, event_id, rating, comments, feedback_date) VALUES
(3, 2, 4, 'Great insights!', '2025-05-16'),
(4, 2, 5, 'Very informative.', '2025-05-16'),
(2, 1, 3, 'Could be better.', '2025-06-11');

INSERT INTO Resources (event_id, resource_type, resource_url, uploaded_at) VALUES
(1, 'pdf', 'https://portal.com/resources/tech_meetup_agenda.pdf', '2025-05-01 10:00:00'),
(2, 'image', 'https://portal.com/resources/ai_poster.jpg', '2025-04-20 09:00:00'),
(3, 'link', 'https://portal.com/resources/html5_docs', '2025-06-25 15:00:00');


-- EXERCISES

-- 1. User Upcoming Events
-- Show upcoming events a user is registered for in their city, sorted by date
SELECT u.full_name, e.title, e.city, e.start_date
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
JOIN Events e ON r.event_id = e.event_id
WHERE e.status = 'upcoming'
AND e.city = u.city
ORDER BY e.start_date;

-- 2. Top Rated Events
-- Events with highest average rating with at least 10 feedback submissions
SELECT e.title, AVG(f.rating) AS avg_rating, COUNT(f.feedback_id) AS total_feedback
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
ORDER BY avg_rating DESC;

-- 3. Inactive Users
-- Users who have not registered for any events in the last 90 days
SELECT u.user_id, u.full_name, u.email
FROM Users u
WHERE u.user_id NOT IN (
    SELECT r.user_id
    FROM Registrations r
    WHERE r.registration_date >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)
);

-- 4. Peak Session Hours
-- Count sessions scheduled between 10 AM to 12 PM for each event
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
WHERE TIME(s.start_time) >= '10:00:00'
AND TIME(s.start_time) < '12:00:00'
GROUP BY e.event_id, e.title;

-- 5. Most Active Cities
-- Top 5 cities with highest number of distinct user registrations
SELECT u.city, COUNT(DISTINCT r.user_id) AS total_registrations
FROM Users u
JOIN Registrations r ON u.user_id = r.user_id
GROUP BY u.city
ORDER BY total_registrations DESC
LIMIT 5;

-- 6. Event Resource Summary
-- Number of resources (PDFs, images, links) uploaded for each event
SELECT e.title,
    SUM(CASE WHEN r.resource_type = 'pdf' THEN 1 ELSE 0 END) AS pdfs,
    SUM(CASE WHEN r.resource_type = 'image' THEN 1 ELSE 0 END) AS images,
    SUM(CASE WHEN r.resource_type = 'link' THEN 1 ELSE 0 END) AS links,
    COUNT(r.resource_id) AS total_resources
FROM Events e
LEFT JOIN Resources r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title;

-- 7. Low Feedback Alerts
-- Users who gave feedback with rating less than 3
SELECT u.full_name, u.email, e.title AS event_name, f.rating, f.comments
FROM Feedback f
JOIN Users u ON f.user_id = u.user_id
JOIN Events e ON f.event_id = e.event_id
WHERE f.rating < 3;

-- 8. Sessions per Upcoming Event
-- Upcoming events with count of sessions scheduled
SELECT e.title, e.start_date, COUNT(s.session_id) AS session_count
FROM Events e
LEFT JOIN Sessions s ON e.event_id = s.event_id
WHERE e.status = 'upcoming'
GROUP BY e.event_id, e.title, e.start_date;

-- 9. Organizer Event Summary
-- For each organizer, number of events and their status
SELECT u.full_name AS organizer,
    COUNT(e.event_id) AS total_events,
    SUM(CASE WHEN e.status = 'upcoming' THEN 1 ELSE 0 END) AS upcoming,
    SUM(CASE WHEN e.status = 'completed' THEN 1 ELSE 0 END) AS completed,
    SUM(CASE WHEN e.status = 'cancelled' THEN 1 ELSE 0 END) AS cancelled
FROM Users u
JOIN Events e ON u.user_id = e.organizer_id
GROUP BY u.user_id, u.full_name;

-- 10. Feedback Gap
-- Events that had registrations but received no feedback
SELECT e.title, COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
WHERE e.event_id NOT IN (
    SELECT DISTINCT event_id FROM Feedback
)
GROUP BY e.event_id, e.title;

-- 11. Daily New User Count
-- Number of users who registered each day in the last 7 days
SELECT registration_date, COUNT(user_id) AS new_users
FROM Users
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 7 DAY)
GROUP BY registration_date
ORDER BY registration_date;

-- 12. Event with Maximum Sessions
-- Event(s) with the highest number of sessions
SELECT e.title, COUNT(s.session_id) AS session_count
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(s.session_id) = (
    SELECT MAX(cnt) FROM (
        SELECT COUNT(session_id) AS cnt
        FROM Sessions
        GROUP BY event_id
    ) AS session_counts
);

-- 13. Average Rating per City
-- Average feedback rating of events in each city
SELECT e.city, ROUND(AVG(f.rating), 2) AS avg_rating
FROM Events e
JOIN Feedback f ON e.event_id = f.event_id
GROUP BY e.city
ORDER BY avg_rating DESC;

-- 14. Most Registered Events
-- Top 3 events based on total user registrations
SELECT e.title, COUNT(r.registration_id) AS total_registrations
FROM Events e
JOIN Registrations r ON e.event_id = r.event_id
GROUP BY e.event_id, e.title
ORDER BY total_registrations DESC
LIMIT 3;

-- 15. Event Session Time Conflict
-- Overlapping sessions within the same event
SELECT s1.event_id, s1.title AS session1, s2.title AS session2,
    s1.start_time AS s1_start, s1.end_time AS s1_end,
    s2.start_time AS s2_start, s2.end_time AS s2_end
FROM Sessions s1
JOIN Sessions s2 ON s1.event_id = s2.event_id
AND s1.session_id < s2.session_id
AND s1.start_time < s2.end_time
AND s1.end_time > s2.start_time;

-- 16. Unregistered Active Users
-- Users who created account in last 30 days but haven't registered for any event
SELECT u.user_id, u.full_name, u.email, u.registration_date
FROM Users u
WHERE u.registration_date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)
AND u.user_id NOT IN (
    SELECT DISTINCT user_id FROM Registrations
);

-- 17. Multi-Session Speakers
-- Speakers handling more than one session across all events
SELECT speaker_name, COUNT(session_id) AS total_sessions
FROM Sessions
GROUP BY speaker_name
HAVING COUNT(session_id) > 1;

-- 18. Resource Availability Check
-- Events that do not have any resources uploaded
SELECT e.title
FROM Events e
WHERE e.event_id NOT IN (
    SELECT DISTINCT event_id FROM Resources
);

-- 19. Completed Events with Feedback Summary
-- Total registrations and average feedback rating for completed events
SELECT e.title,
    COUNT(DISTINCT r.registration_id) AS total_registrations,
    ROUND(AVG(f.rating), 2) AS avg_rating
FROM Events e
LEFT JOIN Registrations r ON e.event_id = r.event_id
LEFT JOIN Feedback f ON e.event_id = f.event_id
WHERE e.status = 'completed'
GROUP BY e.event_id, e.title;

-- 20. User Engagement Index
-- For each user, events attended and feedbacks submitted
SELECT u.full_name,
    COUNT(DISTINCT r.event_id) AS events_registered,
    COUNT(DISTINCT f.feedback_id) AS feedbacks_submitted
FROM Users u
LEFT JOIN Registrations r ON u.user_id = r.user_id
LEFT JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name;

-- 21. Top Feedback Providers
-- Top 5 users who submitted the most feedback
SELECT u.full_name, COUNT(f.feedback_id) AS total_feedback
FROM Users u
JOIN Feedback f ON u.user_id = f.user_id
GROUP BY u.user_id, u.full_name
ORDER BY total_feedback DESC
LIMIT 5;

-- 22. Duplicate Registrations Check
-- Users registered more than once for the same event
SELECT user_id, event_id, COUNT(*) AS registration_count
FROM Registrations
GROUP BY user_id, event_id
HAVING COUNT(*) > 1;

-- 23. Registration Trends
-- Month-wise registration count over the past 12 months
SELECT DATE_FORMAT(registration_date, '%Y-%m') AS month,
    COUNT(registration_id) AS total_registrations
FROM Registrations
WHERE registration_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH)
GROUP BY DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY month;

-- 24. Average Session Duration per Event
-- Average duration in minutes of sessions in each event
SELECT e.title,
    ROUND(AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)), 2) AS avg_duration_minutes
FROM Events e
JOIN Sessions s ON e.event_id = s.event_id
GROUP BY e.event_id, e.title;

-- 25. Events Without Sessions
-- Events that have no sessions scheduled
SELECT e.title, e.status, e.start_date
FROM Events e
WHERE e.event_id NOT IN (
    SELECT DISTINCT event_id FROM Sessions
);