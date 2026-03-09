import { useState } from "react";
import { loginUser } from "../services/authService";
import {Link,useNavigate} from "react-router-dom";


const LoginForm=()=>{
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    const [loading,setLoading]=useState(false);
    const [error,setError]=useState("");
    const naviagte=useNavigate();
    const handleSubmit=async (e:React.FormEvent)=>{
        e.preventDefault();

        setLoading(true);
        setError("");

        try{
            const data=await loginUser(email,password);
            localStorage.setItem("jwt",data.jwt);
            localStorage.setItem("userId",data.id.toString());

            naviagte("/public");
        }catch(err){
            console.log("Login error: ",err);
            setError("Invalid email or password");
        }finally{
            setLoading(false);
        }
    };
    return(
        <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md">
            <h2 className="text-2xl font-bold text-center mb-6">Login to your account</h2>
            {error &&( <p className="text-red-500 text-sm mb-3">{error}</p>)}
            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label className="block text-sm font-medium mb-1">Email</label>
                    <input type="email" className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" 
                    placeholder="Enter your email" value={email} onChange={(e)=>setEmail(e.target.value)} required></input>
                </div>
                <div>
                    <label className="block text-sm font-medium mb-1">Password</label>
                    <input type="password" className="w-full border rounded-lg px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500" 
                    placeholder="Enter your password" value={password} onChange={(e)=>setPassword(e.target.value)} required></input>
                </div>
                <button type="submit" disabled={loading} className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700 transition">
                    {loading?"Logging in...":"Login"}
                </button>
                <p className="text-sm text-center mt-4">Don't have an account?
                <Link to="/signup" className=" text-center text-blue-600 ml-1 " >Sign up</Link>
                </p>
            </form>
            
        </div>
    );
};

export default LoginForm;