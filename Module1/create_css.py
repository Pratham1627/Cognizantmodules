css_content = """/* ========================================
   LOCAL COMMUNITY EVENT PORTAL - CSS3
   ======================================== */

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    font-family: 'Roboto', sans-serif;
    line-height: 1.6;
    color: #333;
    background-color: #f5f5f5;
}

header {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    color: white;
    padding: 40px 20px;
    text-align: center;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

header h1 {
    font-family: 'Poppins', sans-serif;
    font-size: 2.5em;
    font-weight: 700;
    margin-bottom: 10px;
}

header p {
    font-size: 1.1em;
    font-style: italic;
    letter-spacing: 0.5px;
}

.navbar {
    background-color: #2c3e50;
    padding: 0;
    position: sticky;
    top: 0;
    z-index: 100;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.navbar ul {
    list-style-type: none;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
}

.navbar a:link {
    color: white;
    text-decoration: none;
    padding: 15px 20px;
    display: block;
    transition: background-color 0.3s ease;
}

.navbar a:visited {
    color: #ecf0f1;
}

.navbar a:hover {
    background-color: #667eea;
    color: white;
}

.navbar a:active {
    background-color: #764ba2;
    color: #fff;
}

.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

.hero {
    background: linear-gradient(to right, rgba(102, 126, 234, 0.9), rgba(118, 75, 162, 0.9)),
                url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1200 400"><rect fill="%23667eea" width="1200" height="400"/></svg>');
    background-size: cover;
    background-position: center;
    color: white;
    padding: 60px 20px;
    text-align: center;
    border-radius: 8px;
    margin-bottom: 40px;
}

.hero h2 {
    font-family: 'Poppins', sans-serif;
    font-size: 2.2em;
    margin-bottom: 15px;
    font-weight: 700;
}

.cta-button {
    background-color: #007BFF;
    color: white;
    padding: 12px 30px;
    border: none;
    border-radius: 5px;
    font-size: 1em;
    cursor: pointer;
    font-weight: 600;
    transition: background-color 0.3s ease, transform 0.2s ease;
    text-transform: uppercase;
    letter-spacing: 1px;
}

.cta-button:hover {
    background-color: #0056b3;
    transform: translateY(-2px);
    box-shadow: 0 4px 8px rgba(0, 123, 255, 0.3);
}

.events-section {
    margin-bottom: 50px;
}

.events-section h2 {
    font-family: 'Poppins', sans-serif;
    font-size: 2em;
    margin-bottom: 30px;
    text-align: center;
    color: #2c3e50;
    font-weight: 700;
}

.events-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center;
}

.eventCard {
    background-color: white;
    border: 2px solid #e0e0e0;
    border-radius: 8px;
    padding: 25px;
    width: 100%;
    max-width: 350px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.eventCard:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
}

.eventCard h3 {
    color: #667eea;
    margin-bottom: 10px;
    font-size: 1.4em;
    font-weight: 600;
}

.btn-register {
    background-color: #28a745;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    font-weight: 500;
    transition: background-color 0.3s ease;
    width: 100%;
    margin-top: 15px;
}

.btn-register:hover {
    background-color: #218838;
}

.bulletin-section {
    background-color: white;
    padding: 40px;
    border-radius: 8px;
    margin-bottom: 50px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.community-news {
    column-count: 2;
    column-gap: 30px;
    column-rule: 1px solid #ddd;
}

.table-section {
    background-color: white;
    padding: 40px;
    border-radius: 8px;
    margin-bottom: 50px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    overflow-x: auto;
}

.admin-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 0.95em;
}

.admin-table th {
    background-color: #667eea;
    color: white;
    padding: 15px;
    text-align: center;
    font-weight: 600;
    border: 1px solid #555;
}

.admin-table td {
    padding: 12px 15px;
    border: 1px solid #ddd;
    text-align: center;
}

.admin-table tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

.admin-table tbody tr:hover {
    background-color: #f0f0f0;
}

.form-section {
    background-color: white;
    padding: 40px;
    border-radius: 8px;
    margin-bottom: 50px;
    max-width: 600px;
    margin-left: auto;
    margin-right: auto;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    color: #2c3e50;
    font-weight: 500;
}

.form-group input,
.form-group select,
.form-group textarea {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-family: 'Roboto', sans-serif;
    font-size: 1em;
    transition: border-color 0.3s ease;
}

.form-group input:focus,
.form-group select:focus,
.form-group textarea:focus {
    outline: none;
    border-color: #667eea;
    box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
}

.about-section {
    background-color: white;
    padding: 40px;
    border-radius: 8px;
    margin-bottom: 50px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

footer {
    background-color: #2c3e50;
    color: white;
    text-align: center;
    padding: 30px 20px;
    margin-top: 50px;
}

@media (max-width: 768px) {
    header h1 { font-size: 2em; }
    .hero h2 { font-size: 1.7em; }
    .events-grid { flex-direction: column; align-items: center; }
    .navbar ul { flex-direction: column; }
    .community-news { column-count: 1; }
}

@media (max-width: 480px) {
    body { font-size: 14px; }
    header { padding: 30px 15px; }
    header h1 { font-size: 1.5em; }
    .container { padding: 10px; }
}
"""

with open('style.css', 'w') as f:
    f.write(css_content)

print("CSS file successfully created!")
