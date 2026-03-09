import { useState } from "react";
import { signupUser } from "../services/authService";
import { useNavigate,Link } from "react-router-dom";


const SignupForm=()=>{
    const[name,setName]=useState("");
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");

    const[loading , setLoading]=useState(false);
    const[error,setError]=useState("");
    const[success,setSuccess]=useState("");
    const navigate=useNavigate();
    const handleSubmit=async(e:React.FormEvent)=>{
        e.preventDefault();

        setLoading(true);
        setError("");

        

        try{
            await signupUser(name,email,password);
            navigate("/")
            setSuccess("Account created successfully! Please login. ");
            setName("");
            setEmail("");
            setPassword("");
        }catch(err:any){
            const message=err.response?.data?.message||
                        err.response?.data?.err||
                        "Sign up Failed.Try Again.."
            setError(message);
        }finally{
            setLoading(false);
        }
    };
    return(
        <div className="bg-white shadow-lg rounded-xl p-8 w-full max-w-md">
            <h2 className="text-2xl font-bold text-center mb-6">Create an account</h2>
            {error && <p className="text-red-500 text-sm mb-2">{error}</p>}
            {success && <p className="text-green-500 text-sm mb-2">{success}</p>}

            <form onSubmit={handleSubmit} className="space-y-4">
                <div>
                    <label>Name</label>
                    <input type="text" placeholder="Enter your name" value={name} onChange={(e)=>setName(e.target.value)}
                    className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-500" required></input>
                </div>
                <div>
                    <label>Email</label>
                    <input type="email" placeholder="Enter your email" value={email} onChange={(e)=>setEmail(e.target.value)}
                    className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-500" required></input>
                </div>
                <div>
                    <label className="text-sm font-medium block mb-1">Password</label>
                    <input type="password" placeholder="Create password" value={password} onChange={(e)=>setPassword(e.target.value)}
                    className="w-full border rounded-lg px-3 py-2 focus:ring-2 focus:ring-blue-500" required></input>
                </div>
                <button type="submit" disabled={loading} className="w-full bg-blue-600 text-white py-2 rounded-lg hover:bg-blue-700">
                    {loading ? "Creating Account...":"Sign Up"}
                </button>
                <p className="text-sm text-center mt-4">Already have an account? 
                <Link to="/signup" className="text-blue-600 ml-1">Login</Link>
             </p>
            </form>
             
        </div>
       
    );
    
};
export default SignupForm;