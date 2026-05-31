function enlargeImg(img) {
	img.style.width = img.style.width === '300px' ? '160px' : '300px';
	img.style.height = img.style.height === '200px' ? '110px' : '200px';
}

function openGalleryImage(img) {
	var modal = document.getElementById('galleryModal');
	var modalImg = document.getElementById('galleryModalImg');
	var modalCaption = document.getElementById('galleryModalCaption');

	modalImg.src = img.src;
	modalImg.alt = img.alt;
	modalCaption.textContent = img.title || img.alt;
	modal.classList.add('is-open');
	modal.setAttribute('aria-hidden', 'false');
}

function getCurrentUser() {
	var raw = localStorage.getItem('currentUser');
	if (!raw) {
		return null;
	}
	try {
		return JSON.parse(raw);
	} catch (error) {
		return null;
	}
}

function setAuthStatus() {
	var status = document.getElementById('authStatus');
	if (!status) {
		return;
	}
	var currentUser = getCurrentUser();
	if (currentUser && currentUser.name) {
		status.innerHTML = '<span class="dot"></span> Signed in as <strong>' + currentUser.name + '</strong> <a href="auth.html">Switch account</a>';
		return;
	}
	status.innerHTML = '<span class="dot"></span> Not signed in <a href="auth.html">Sign in / Sign up</a>';
}

function setAuthMessage(message, isError) {
	var output = document.getElementById('authMessage');
	if (!output) {
		return;
	}
	output.textContent = message;
	output.style.color = isError ? '#b91c1c' : '#0f172a';
}

function loadAuthForm(mode) {
	var signupForm = document.getElementById('signupForm');
	var loginForm = document.getElementById('loginForm');
	var signupTab = document.getElementById('signupTab');
	var loginTab = document.getElementById('loginTab');
	if (!signupForm || !loginForm || !signupTab || !loginTab) {
		return;
	}
	var isSignup = mode === 'signup';
	signupForm.classList.toggle('active', isSignup);
	loginForm.classList.toggle('active', !isSignup);
	signupTab.classList.toggle('active', isSignup);
	loginTab.classList.toggle('active', !isSignup);
	setAuthMessage(isSignup ? 'Create your portal account to register for events.' : 'Sign in to continue with your saved portal profile.', false);
}

function getUsers() {
	var raw = localStorage.getItem('portalUsers');
	if (!raw) {
		return [];
	}
	try {
		return JSON.parse(raw) || [];
	} catch (error) {
		return [];
	}
}

function saveUsers(users) {
	localStorage.setItem('portalUsers', JSON.stringify(users));
}

function handleSignup(event) {
	event.preventDefault();
	var name = document.getElementById('signupName').value.trim();
	var email = document.getElementById('signupEmail').value.trim().toLowerCase();
	var password = document.getElementById('signupPassword').value;
	var confirmPassword = document.getElementById('signupConfirmPassword').value;

	if (!name || !email || !password) {
		setAuthMessage('Please fill in all sign-up fields.', true);
		return;
	}

	if (password !== confirmPassword) {
		setAuthMessage('Passwords do not match.', true);
		return;
	}

	var users = getUsers();
	var existingUser = users.find(function(user) {
		return user.email === email;
	});

	if (existingUser) {
		setAuthMessage('That email is already registered. Please sign in instead.', true);
		loadAuthForm('login');
		return;
	}

	users.push({ name: name, email: email, password: password });
	saveUsers(users);
	localStorage.setItem('currentUser', JSON.stringify({ name: name, email: email }));
	setAuthStatus();
	setAuthMessage('Account created. You are now signed in as ' + name + '.', false);
	log('New user registered: ' + email);
	document.getElementById('signupForm').reset();
}

function handleLogin(event) {
	event.preventDefault();
	var email = document.getElementById('loginEmail').value.trim().toLowerCase();
	var password = document.getElementById('loginPassword').value;

	if (!email || !password) {
		setAuthMessage('Enter your email and password to sign in.', true);
		return;
	}

	var users = getUsers();
	var matchedUser = users.find(function(user) {
		return user.email === email && user.password === password;
	});

	if (!matchedUser) {
		setAuthMessage('No matching account was found. Please sign up first or check your password.', true);
		return;
	}

	localStorage.setItem('currentUser', JSON.stringify({ name: matchedUser.name, email: matchedUser.email }));
	setAuthStatus();
	setAuthMessage('Welcome back, ' + matchedUser.name + '.', false);
	log('User signed in: ' + email);
	document.getElementById('loginForm').reset();
}

function signOutUser() {
	localStorage.removeItem('currentUser');
	setAuthStatus();
	setAuthMessage('You have been signed out.', false);
}

function closeGalleryImage() {
	var modal = document.getElementById('galleryModal');
	modal.classList.remove('is-open');
	modal.setAttribute('aria-hidden', 'true');
}

document.addEventListener('click', function(event) {
	var modal = document.getElementById('galleryModal');
	if (event.target === modal) {
		closeGalleryImage();
	}
});

document.addEventListener('keydown', function(event) {
	if (event.key === 'Escape') {
		closeGalleryImage();
	}
});

function submitForm(e) {
	e.preventDefault();
	var name = document.getElementById('uname').value;
	var event = document.getElementById('eventType').value;
	document.getElementById('formOutput').innerText = 'Thanks ' + name + '! Registered for: ' + event;
	log('Registration submitted: ' + name + ' | ' + event);
}

function validatePhone() {
	var phone = document.getElementById('phone').value;
	var msg = document.getElementById('phoneMsg');
	if (phone.length === 10 && !isNaN(phone)) {
		msg.style.color = 'green';
		msg.innerText = ' Valid';
	} else {
		msg.style.color = 'red';
		msg.innerText = ' Enter 10-digit number';
	}
}

function showFee(sel) {
	var fee = sel.value;
	document.getElementById('feeMsg').innerText = fee ? 'Registration Fee: ' + fee : '';
}

function countChars() {
	var len = document.getElementById('feedbackText').value.length;
	document.getElementById('charCount').innerText = '(' + len + '/200)';
}

function savePref() {
	var pref = document.getElementById('prefSelect').value;
	if (!pref) {
		document.getElementById('prefMsg').innerText = 'Select an event first.';
		return;
	}
	localStorage.setItem('prefEvent', pref);
	sessionStorage.setItem('prefEvent', pref);
	document.getElementById('prefMsg').innerText = 'Saved: ' + pref;
	log('Preference saved: ' + pref);
}

function clearPref() {
	localStorage.clear();
	sessionStorage.clear();
	document.getElementById('prefSelect').value = '';
	document.getElementById('prefMsg').innerText = 'Preferences cleared!';
	log('localStorage and sessionStorage cleared');
}

window.onload = function() {
	setAuthStatus();
	var saved = localStorage.getItem('prefEvent');
	var prefSelect = document.getElementById('prefSelect');
	var prefMsg = document.getElementById('prefMsg');
	if (saved && prefSelect && prefMsg) {
		prefSelect.value = saved;
		prefMsg.innerText = 'Loaded: ' + saved;
	}
	log('Portal loaded. Saved preference: ' + (saved || 'None'));
};

function findEvents() {
	var div = document.getElementById('geoResult');
	div.style.display = 'block';
	div.innerHTML = 'Getting your location...';

	if (!navigator.geolocation) {
		div.innerHTML = 'Geolocation not supported.';
		return;
	}

	navigator.geolocation.getCurrentPosition(
		function(pos) {
			var lat = pos.coords.latitude.toFixed(4);
			var lon = pos.coords.longitude.toFixed(4);
			div.innerHTML = 'Lat: <b>' + lat + '</b> | Lon: <b>' + lon + '</b><br>3 events found near you!';
			log('Location: ' + lat + ', ' + lon);
		},
		function(err) {
			var msgs = ['', 'Permission denied.', 'Location unavailable.', 'Request timed out.'];
			div.innerHTML = msgs[err.code] || 'Location error.';
			log('Geo error: ' + (msgs[err.code] || 'Location error.'));
		},
		{ enableHighAccuracy: true, timeout: 8000, maximumAge: 0 }
	);
}

function log(msg) {
	var d = document.getElementById('activityLog') || document.getElementById('debugLog');
	if (!d) {
		return;
	}
	d.innerHTML += '<br>[' + new Date().toLocaleTimeString() + '] ' + msg;
	d.scrollTop = d.scrollHeight;
	console.log('[PORTAL]', msg);
}

function runActivityCheck() {
	var x = 10, y = 20;
	log('Status check: x=' + x + ', y=' + y + ', sum=' + (x + y));
	log('Registered name: ' + (document.getElementById('uname')?.value || '(empty)'));
	log('Saved keys: ' + (Object.keys(localStorage).join(', ') || 'none'));
}
