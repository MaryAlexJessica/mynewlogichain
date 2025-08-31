import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../../css/Login.css";

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
  const handleLogin = (e) => {
    e.preventDefault();

    if (validateForm()) {
      if (email === "selvu14@yahoo.in" && password === "123456") {
        onLogin(email); // ✅ tell App.jsx that user logged in
      } else {
        alert("Invalid credentials (use selvu14@yahoo.in / 123456)");
      }
    }
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

          <div className="forgot-link">Forgot your password?</div>

          <button type="submit" className="btn btn-signin">
            Sign In
          </button>
        </form>
      </div>

      {/* Right Side: Sign up */}
      <div className="login-right">
        <h2>Create, Account!</h2>
        <p>Sign up if you still don't have an account...</p>
        <button className="btn btn-signup">Sign Up</button>
      </div>
    </div>
  );
};

export default Login;