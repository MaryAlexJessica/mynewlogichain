import { useEffect, useState } from "react";
import { getUser } from "../../services/userProfileService";
import { getRoles } from "../../services/roleService";

const UserProfile = () => {
  const [user, setUser] = useState(null);
  const [roles, setRoles] = useState([]); // ✅ added state

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("user"));
    const email = storedUser?.email;

    if (!email) return;

    getUser().then((users) => {
      const foundUser = users.find((u) => u.email === email);
      setUser(foundUser);
      console.log("Fetched user details:", foundUser);
    });

    getRoles().then((rolesData) => {
      setRoles(rolesData);
      console.log("Fetched roles:", rolesData);
    });
  }, []);

  if (!user) return <div>Loading...</div>;

  // ✅ Match using role_id and role_name
  const roleName =
    roles.find((r) => String(r.role_id) === String(user.role_id))?.role_name ||
    "Unknown Role";

  return (
    <div>
      <h2>{user.full_name || user.username || "User"}</h2>
      <p>Username: {user.username || "N/A"}</p>
      <p>Email: {user.email}</p>
      <p>Role: {roleName}</p>
      <p>Created At: {user.created_at || "N/A"}</p>
    </div>
  );
};

export default UserProfile;
