import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../analytics/Login.css";
import { getUser } from "../../services/userProfileService";
import bcrypt from "bcryptjs";
import { roles } from "../../mock/Roles";
import { roleRoutes } from "../../config/roleRoute.js";

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
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
  // Handle Login
  const handleLogin = async (e) => {
    e.preventDefault();

    if (!validateForm()) return;

    try {
      const users = await getUser();
      console.log("🔍 Users fetched from mock:", users);
      console.log("📝 Login attempt:", { email, password });

      let foundUser = null;

      for (let u of users) {
        if (u.email === email) {
          const isMatch = bcrypt.compareSync(password, u.password);
          console.log(`Password match for ${u.email}:`, isMatch);
          if (isMatch) {
            foundUser = u;
            break;
          }
        }
      }

      if (foundUser) {
        console.log("✅ Found user:", foundUser);

        // 🔹 Store only minimal user data
        const userData = {
          email: foundUser.email,
          role_id: foundUser.role_id,
          username: foundUser.username,  // ✅ add this
        };

        localStorage.setItem("user", JSON.stringify(userData));
        console.log("📦 UserData stored in localStorage:", userData);

        if (onLogin) onLogin(userData);

        // 🔹 Redirect by role_id
        // After successful login
        // After successful login
        const redirectPath = roleRoutes[foundUser.role_id]?.default || "/unauthorized";
        console.log("redirectPath", redirectPath);
        navigate(redirectPath);
        console.log("foundUser.role_id", foundUser.role_id);

        navigate(redirectPath);

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
      const user = users.find((u) => u.email === emailInput);
      if (user) {
        alert("Password reset link has been sent to your email!");
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

          {errors.form && <p className="error-text">{errors.form}</p>}

          <button type="submit" className="btn btn-signin">
            Sign In
          </button>
        </form>
      </div>

      {/* Right Side: Sign up */}
      <div className="login-right">
        <h2>Create Account!</h2>
        <p>Sign up if you still don't have an account...</p>
        <button
          className="btn btn-signup"
          onClick={() => navigate("/signup")}
        >
          Sign Up
        </button>
      </div>
    </div>
  );
};

export default Login;
