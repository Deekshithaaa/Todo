import { createUserApi, checkUsernameApi} from './api/TodoApiService';
import React, { Component } from "react";
import './css/RegistrationComponent.css';
import swal from 'sweetalert';

class RegistrationComponent extends Component {

  constructor(props) {
    super(props);

    this.state = {
      username: '',
      password: '',
      confirmPassword: '', 
      mail_id: '',
      registrationSuccessful: false,
      passwordMismatch: false,
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  }

  handleSubmit = (event) => {
    event.preventDefault();

    if (this.state.password !== this.state.confirmPassword) { // 3. Check if passwords match
      this.setState({ passwordMismatch: true });
      return;
    }

    const userRegistration = {
      username: this.state.username,
      password: this.state.password,
      mail_id: this.state.mail_id,
    };

    return checkUsernameApi(userRegistration)
          .then(response => {
              if(response.data === true){

                swal('User already exists!', 'Please try a different username.', 'error');


                  
              } else if(response.data === false) {

                createUserApi(userRegistration)
      .then((response) => {
        console.log('User registered:', response.data);
        this.setState({ registrationSuccessful: true });
      })
      .catch((error) => {
        console.error('Error registering user:', error);
        // Handle the error, e.g., display an error message
      });
                  
              }
          })
          .catch(error => {
              console.log(error);
              return Promise.reject(error); 
          });

    

    console.log('Registration Data:', this.state);
  }

  render() {
    const { registrationSuccessful, passwordMismatch } = this.state;

    if (registrationSuccessful) {
      return (
        <div className="success-container">
          <h2>Registration Successful</h2>
          <p>Registration was successful. You can now navigate to the login page.</p>
        </div>
      );
    }

    return (
      <div className="registration-container">
        <h2>User Registration</h2>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group">
            <label>Username:</label>
            <input
              type="text"
              name="username"
              value={this.state.username}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div className="form-group">
            <label>Password:</label>
            <input
              type="password"
              name="password"
              value={this.state.password}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <div className="form-group"> {/* 2. Add confirm password input */}
            <label>Confirm Password:</label>
            <input
              type="password"
              name="confirmPassword"
              value={this.state.confirmPassword}
              onChange={this.handleInputChange}
              required
            />
          </div>
          {passwordMismatch && <p className="error-message">Passwords do not match!</p>} {/* Show error message if passwords don't match */}
          <div className="form-group">
            <label>Email:</label>
            <input
              type="email" // Change type to "email" for proper email validation
              name="mail_id"
              value={this.state.mail_id}
              onChange={this.handleInputChange}
              required
            />
          </div>
          <button type="submit">Register</button>
        </form>
      </div>
    );
  }
}

export default RegistrationComponent;
