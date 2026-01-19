<h1>🏥 Healthcare Appointment System</h1>

<p>
<strong>Healthcare Appointment System</strong> is a console-based Java application for managing medical appointments
using role-based menus and a layered architecture.
</p>

<p>
This is an educational <strong>pet project</strong> focused on clean backend design
and practical Java development.
</p>

<h2>📌 Project Overview</h2>

<ul>
  <li>manage patients and doctors</li>
  <li>create and view appointments</li>
  <li>interact with the system via role-based CLI menus</li>
</ul>

<p>
The application currently works <strong>without authentication</strong>.
User roles are handled through separate menus and workflows.
</p>

<h2>🧭 Application Menus</h2>

<h3>Administrator</h3>
<ul>
  <li>Add new patients</li>
  <li>View all patients</li>
  <li>Add new doctors</li>
  <li>Delete doctors</li>
  <li>View all doctors</li>
</ul>

<h3>Doctor</h3>
<ul>
  <li>View assigned appointments</li>
  <li>Filter appointments by patient</li>
  <li>Filter appointments by date</li>
  <li>Filter appointments by time</li>
</ul>

<h3>Patient</h3>
<ul>
  <li>View own appointments</li>
  <li>Create an appointment</li>
</ul>

<h2>🏗️ Architecture</h2>

<pre>
presentation → service → repository → model
</pre>

<ul>
  <li><strong>Model</strong> — domain entities (Patient, Doctor, Appointment)</li>
  <li><strong>Repository</strong> — in-memory data storage</li>
  <li><strong>Service</strong> — business logic and validation</li>
  <li><strong>Presentation</strong> — CLI menus and user interaction</li>
</ul>

<h2>🛠️ Technologies &amp; Tools</h2>

<table>
  <tr>
    <th>Category</th>
    <th>Technology</th>
  </tr>
  <tr>
    <td>Programming</td>
    <td>Java, Object-Oriented Programming (OOP)</td>
  </tr>
  <tr>
    <td>Architecture</td>
    <td>Layered Architecture (Presentation / Service / Repository)</td>
  </tr>
  <tr>
    <td>Core Java Concepts</td>
    <td>Exception Handling, Java Collections, Input Validation</td>
  </tr>
  <tr>
    <td>User Interface</td>
    <td>Console-based UI (CLI)</td>
  </tr>
  <tr>
    <td>Development Tools</td>
    <td>Git &amp; GitHub, IntelliJ IDEA</td>
  </tr>
</table>


<h2>📁 Project Structure</h2>

<pre>
at.austrian.healthcare
 ├── model
 ├── repository
 ├── service
 ├── presentation
 └── util
</pre>

<h2>▶️ How to Run</h2>

<ol>
  <li>Clone the repository</li>
  <li>Open the project in IntelliJ IDEA</li>
  <li>Run the <code>Main</code> class</li>
  <li>Use the console menu to interact with the system</li>
</ol>

<h2>🚀 Planned Improvements</h2>

<ul>
  <li>Authentication (admin, doctor, patient login)</li>
  <li>Replace in-memory storage with persistent database</li>
  <li>Introduce repository interfaces for better abstraction</li>
  <li>Appointment cancellation and rescheduling</li>
  <li>Improved error handling and custom exceptions</li>
  <li>Unit tests</li>
</ul>

<h2>📄 License</h2>

<p>
This project is created for educational purposes.
</p>


