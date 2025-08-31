import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../analytics/Login.css";
import { getUser } from "../../services/userProfileService";
import bcrypt from "bcryptjs";

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  // Form Validation
  const validateForm = () => {
    const newErrors = {};

    if (!email) {
      newErrors.email = "Email is required";
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      newErrors.email = "Invalid email format";
    }

    if (!password) {
      newErrors.password = "Password is required";
    } else if (password.length < 6) {
      newErrors.password = "Password must be at least 6 characters";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

// Handle Login
const handleLogin = async (e) => {
  e.preventDefault();

  if (!validateForm()) return; // validate first

  try {
    const users = await getUser(); // fetch users
    const foundUser = users.find(
      (u) => u.email === email && bcrypt.compareSync(password, u.password)
    );

    if (foundUser) {
      // Save user email in localStorage
      localStorage.setItem("user", foundUser.email); // store only email

      // Call parent callback if needed
      if (onLogin) onLogin(foundUser.email); // pass only email
 

      navigate("/dashboard"); // redirect
    } else {
      setErrors({ form: "Invalid email or password" });
    }
  } catch (err) {
    console.error("Login error:", err);
    setErrors({ form: "Something went wrong. Please try again." });
  }
};
  // 🔹 Handle Forgot Password
  const handleForgotPassword = () => {
    const emailInput = prompt("Enter your registered email to reset password:");
    if (!emailInput) return;

    getUser().then((users) => {
      const user = users.find(u => u.email === emailInput);
      if (user) {
        alert("Password reset link has been sent to your email!");
        // Optional: integrate real email service here
      } else {
        alert("Email not found!");
      }
    });
  };
  return (
    <div className="login-container">
      {/* Left Side: Sign in */}
      <div className="login-left">
        <h2>Sign in</h2>

        <form onSubmit={handleLogin}>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          {errors.email && <p className="error-text">{errors.email}</p>}

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          {errors.password && <p className="error-text">{errors.password}</p>}
          <span className="forgot-link" onClick={handleForgotPassword}>
            Forgot your password?
          </span>
            {/* Form-level error message */}
            {errors.form && <p className="error-text">{errors.form}</p>}
            <button type="submit" className="btn btn-signin">
              Sign In
            </button>
        </form>
      </div>

      {/* Right Side: Sign up */}
      <div className="login-right">
        <h2>Create, Account!</h2>
        <p>Sign up if you still don't have an account...</p>
            <button className="btn btn-signup" onClick={() => navigate("/signup")}>
             Sign Up
            </button>     
      </div>
    </div>
  );
};

export default Login;