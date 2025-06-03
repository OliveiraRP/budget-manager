import "./styles/App.css";
import { useAuthUser } from "./hooks/useAuthUser";

function App() {
  const { user, error } = useAuthUser();

  return (
    <div className="App">
      {error && <h1 style={{ color: "red" }}>Error: {error}</h1>}
      {!error && user && <h1>Hello, {user.name}</h1>}
      {!error && !user && <h1>Loading...</h1>}
    </div>
  );
}

export default App;
