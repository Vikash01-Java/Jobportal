const BASE_URL = "http://localhost:8082/api";

/* ================= USERS ================= */

function createUser() {
    const user = {
        name: document.getElementById("name").value,
        email: document.getElementById("email").value
    };

    fetch(BASE_URL + "/users", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(user)
    })
    .then(res => res.json())
    .then(() => {
        alert("User Created ✅");
        loadUsers();
    });
}

function loadUsers() {
    fetch(BASE_URL + "/users")
    .then(res => res.json())
    .then(data => {
        let html = "";
        data.forEach(u => {
            html += `<div class="card">
                <h3>${u.name}</h3>
                <p>${u.email}</p>
            </div>`;
        });
        document.getElementById("userList").innerHTML = html;
    });
}

/* ================= JOBS ================= */

function createJob() {
    const job = {
        title: document.getElementById("title").value,
        description: document.getElementById("description").value,
        salary: document.getElementById("salary").value
    };

    fetch(BASE_URL + "/jobs", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(job)
    })
    .then(res => res.json())
    .then(() => {
        alert("Job Created ✅");
        loadJobs();
    });
}

function showJobs(data){
    let html = "";
    data.forEach(j => {
        html += `
        <div class="card">
            <h3>${j.title}</h3>
            <p>${j.description}</p>
            <p>₹${j.salary}</p>
            <button onclick="applyJob(${j.id})">Apply</button>
            <button onclick="updateJob(${j.id})">Edit</button>
            <button onclick="deleteJob(${j.id})">Delete</button>
        </div>`;
    });
    document.getElementById("jobList").innerHTML = html;
}

/* ================= APPLICATION ================= */

function applyJob() {
    const app = {
        userId: document.getElementById("userId").value,
        jobId: document.getElementById("jobId").value,
        status: document.getElementById("status").value
    };

    fetch(BASE_URL + "/applications", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(app)
    })
    .then(res => res.json())
    .then(() => alert("Applied Successfully ✅"));
}
// DELETE USER
function deleteUser(id) {
    fetch(BASE_URL + "/users/" + id, {
        method: "DELETE"
    })
    .then(() => {
        alert("User Deleted ❌");
        loadUsers();
    });
}

// UPDATE USER
function updateUser(id) {
    const name = prompt("Enter new name:");
    const email = prompt("Enter new email:");

    fetch(BASE_URL + "/users/" + id, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({name, email})
    })
    .then(res => res.json())
    .then(() => {
        alert("User Updated ✅");
        loadUsers();
    });
}

// SEARCH USER
function searchUser() {
    const name = document.getElementById("searchName").value;

    fetch(BASE_URL + "/users/name/" + name)
    .then(res => res.json())
    .then(data => showUsers(data));
}

// SHOW USERS
function showUsers(data){
    let html = "";

    data.forEach(u => {
        html += `
        <div class="card">
            <h3>${u.name}</h3>
            <p>${u.email}</p>
            <p><b>ID: ${u.id}</b></p>
            <button onclick="updateUser(${u.id})">Edit</button>
            <button onclick="deleteUser(${u.id})">Delete</button>
        </div>`;
    });

    document.getElementById("userList").innerHTML = html;
}

// UPDATE loadUsers
function loadUsers() {
    fetch(BASE_URL + "/users")
    .then(res => res.json())
    .then(data => showUsers(data));
}
function deleteJob(id) {
    fetch(BASE_URL + "/jobs/" + id, {
        method: "DELETE"
    })
    .then(() => {
        alert("Job Deleted ❌");
        loadJobs();
    });
}

function updateJob(id) {
    const title = prompt("New title:");
    const description = prompt("New description:");
    const salary = prompt("New salary:");

    fetch(BASE_URL + "/jobs/" + id, {
        method: "PUT",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({title, description, salary})
    })
    .then(res => res.json())
    .then(() => {
        alert("Job Updated ✅");
        loadJobs();
    });
}

function searchJob() {
    const title = document.getElementById("searchTitle").value;

    fetch(BASE_URL + "/jobs/title/" + title)
    .then(res => res.json())
    .then(data => showJobs(data));
}

function showJobs(data){
    let html = "";

    data.forEach(j => {
        html += `
        <div class="card">
		<p><b>Job ID: ${j.id}</b></p>
            <h3>${j.title}</h3>
            <p>${j.description}</p>
            <p>₹${j.salary}</p>
            <button onclick="applyJob(${j.id})">Apply</button>
			<button onclick="updateJob(${j.id})">Edit</button>
			            <button onclick="deleteJob(${j.id})">Delete</button>
        </div>
        `;
    });

    document.getElementById("jobList").innerHTML = html;
}

function loadJobs() {
    fetch(BASE_URL + "/jobs")
    .then(res => res.json())
    .then(data => showJobs(data));
	
}
function deleteApplication(id) {
    fetch(BASE_URL + "/applications/" + id, {
        method: "DELETE"
    })
    .then(() => alert("Application Deleted ❌"));
}

function updateStatus(id) {
    const status = prompt("Enter new status:");

    fetch(BASE_URL + "/applications/" + id + "/status?status=" + status, {
        method: "PUT"
    })
    .then(res => res.json())
    .then(() => alert("Status Updated ✅"));
}
function applyJob(jobId){
    const userId = prompt("Enter your User ID:");

    fetch(BASE_URL + "/applications", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: userId,
            jobId: jobId,
            status: "APPLIED"
        })
    })
    .then(res => res.json())
    .then(() => alert("Applied Successfully ✅"));
}
function loadUserDropdown() {
    fetch(BASE_URL + "/users")
    .then(res => res.json())
    .then(data => {
        let options = "";
        data.forEach(u => {
            options += `<option value="${u.id}">${u.name}</option>`;
        });
        document.getElementById("userSelect").innerHTML = options;
    });
}
function applyJob(jobId){
    const userId = document.getElementById("userSelect").value;

    fetch(BASE_URL + "/applications", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            userId: userId,
            jobId: jobId,
            status: "APPLIED"
        })
    })
    .then(res => res.json())
    .then(() => alert("Applied Successfully ✅"));
}

function loadApplications() {
    fetch(BASE_URL + "/applications")
    .then(res => res.json())
    .then(data => showApplications(data));
}

function showApplications(data){
    let html = "";

    data.forEach(a => {
        html += `
        <div class="card">
            <p><b>Application ID:</b> ${a.id}</p>
            <p><b>User ID:</b> ${a.userId}</p>
            <p><b>Job ID:</b> ${a.jobId}</p>
            <p><b>Status:</b> ${a.status}</p>

            <button onclick="updateStatus(${a.id})">Update Status</button>
            <button onclick="deleteApplication(${a.id})">Delete</button>
        </div>
        `;
    });

    document.getElementById("appList").innerHTML = html;
}
function getJobById(){
    const id = document.getElementById("jobIdSearch").value;

    fetch(BASE_URL + "/jobs/" + id)
    .then(res => res.json())
    .then(data => {
        showJobs([data]); // single object ko array me convert
    });
}
function getJobsBySalary(){
    const salary = document.getElementById("salarySearch").value;

    fetch(BASE_URL + "/jobs/salary/" + salary)
    .then(res => res.json())
    .then(data => showJobs(data));
}
function getApplicationById(){
    const id = document.getElementById("appIdSearch").value;

    fetch(BASE_URL + "/applications/" + id)
    .then(res => res.json())
    .then(data => showApplications([data]));
}