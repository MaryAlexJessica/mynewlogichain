import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import bcrypt from "bcryptjs";
import "../analytics/Login.css";
import { getUser, saveUser } from "../../services/userProfileService";
import { Roles } from "../../mock/Roles";

const Signup = ({ onSignup }) => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [roleId, setRoleId] = useState(
    Roles.find(r => r.role_name === "Customer").role_id); // default role
  const [errors, setErrors] = useState({});
  const navigate = useNavigate();

  const validateForm = () => {
    const newErrors = {};
    if (!username) newErrors.username = "Username is required";
    if (!email) newErrors.email = "Email is required";
    else if (!/\S+@\S+\.\S+/.test(email)) newErrors.email = "Invalid email format";
    if (!password) newErrors.password = "Password is required";
    else if (password.length < 6) newErrors.password = "Password must be at least 6 characters";

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSignup = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const users = await getUser();
      if (users.some(u => u.email === email)) {
        setErrors({ form: "Email already registered" });
        return;
      }

      const hashedPassword = bcrypt.hashSync(password, 10);

      const newUser = {
        username,
        email,
        password: hashedPassword,
        role_id: roleId,
        created_at: new Date().toISOString(),
      };

      await saveUser(newUser);

      if (onSignup) onSignup(email);
      navigate("/dashboard");
    } catch (err) {
      console.error(err);
      setErrors({ form: "Something went wrong. Please try again." });
    }
  };

  return (
    <div className="login-container">
      <div className="signup-left">
        <h2>Create Account</h2>
        <form onSubmit={handleSignup}>
          <input
            type="text"
            placeholder="Username"
            className="input-field"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />
          {errors.username && <p className="error-text">{errors.username}</p>}

          <input
            type="email"
            placeholder="Email"
            className="input-field"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          {errors.email && <p className="error-text">{errors.email}</p>}

          <input
            type="password"
            placeholder="Password"
            className="input-field"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          {errors.password && <p className="error-text">{errors.password}</p>}

          {/* Role selection */}
          <select className="input-field"
            value={roleId}
            onChange={(e) => setRoleId(Number(e.target.value))}
          >
            {Roles.map((role) => (
              <option key={role.role_id} value={role.role_id}>
                {role.role_name}
              </option>
            ))}
          </select>

          {errors.form && <p className="error-text">{errors.form}</p>}
           <button type="submit" className="btn btn-signin">
             Sign Up
           </button>
        </form>

        <div style={{ marginTop: "10px" }}>
          Already have an account?{" "}
          <span
            style={{ color: "#007bff", cursor: "pointer" }}
            onClick={() => navigate("/login")}
          >
            Sign In
          </span>
        </div>
      </div>

      <div className="login-right">
        <h2>Welcome!</h2>
        <p>Fill in the details to register and access the dashboard.</p>
      </div>
    </div>
  );
};

export default Signup;
