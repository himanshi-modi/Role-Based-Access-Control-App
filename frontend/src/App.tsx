import { BrowserRouter, Routes ,Route} from "react-router-dom";
import LoginPage  from "./pages/LoginPage";
import SignupPage from "./pages/SignupPage";
import DashboardPage from "./pages/DasboardPage";


function App(){
  return(
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<LoginPage />} ></Route>
      <Route path="/signup" element={<SignupPage />}></Route>
      <Route path="/public" element={<DashboardPage />}></Route>
    </Routes>
    </BrowserRouter>
  );
}
export default App;