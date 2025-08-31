import { useEffect, useState } from "react";
import { getUser } from "../../services/userProfileService";

const UserProfile = () => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const email = localStorage.getItem("user"); // ✅ get email from localStorage
    if (!email) return;

    getUser().then((users) => {
      const foundUser = users.find((u) => u.email === email);
      setUser(foundUser);
      console.log("Fetched user details:", foundUser);
    });
  }, []);

  if (!user) return <div>Loading...</div>;

  return (
    <div>
      <h2>{user.full_name}</h2>
      <p>Username: {user.username}</p>
      <p>Email: {user.email}</p>
      <p>Role ID: {user.role_id}</p>
      <p>Created At: {user.created_at}</p>
    </div>
  );
};

export default UserProfile;
