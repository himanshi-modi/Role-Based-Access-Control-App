import { useEffect, useState } from "react";
import { fetchUserContent,fetchAdminContent } from "../services/dashboardService";
import { useNavigate } from "react-router-dom";
import jwt_decode from "jwt-decode";



interface TokenPayload {
    roles:string,
    email:string,
    sub:string
}
const DashboardPage= ()=>{
    const [userContent, setUserContent]=useState<string>("");
    const [adminContent, setAdminContent]=useState<string>("");
    const [loadingUser,setLoadingUser]=useState(false);
    const [loadingAdmin,setLoadingAdmin]=useState(false);
    const [error, setError]=useState("");
    const [role,setRole]=useState<"USER" | "ADMIN" | null>(null);
    const navigate=useNavigate();
    const handleLogout=()=>{
        localStorage.removeItem("jwt");
        localStorage.removeItem("id");
        navigate("/");
    }
    
    useEffect(()=>{
        const jwt= localStorage.getItem("jwt");
        if(!jwt){
            navigate("/");
            return;
        }
        try{
            const decoded:TokenPayload= jwt_decode(jwt);
            if(decoded.roles==="USER" || decoded.roles==="ADMIN"){
                setRole(decoded.roles);
            }else{
                navigate("/")
            }
        }catch{
            navigate("/");
        }
    },[navigate]);
    const handleUserClick= async()=>{
        if(role!="USER"){
            setError("Access Denied: You are not allowed to view User Dashboard");
            setUserContent("");
            return;
        }
        setLoadingUser(true);
        setError("");
        try{
            const data= await fetchUserContent();
            setUserContent(data);
        }catch(err:any){
            setError(err.response?.data?.message || "Failed to fetch User Content");
        }finally{
            setLoadingUser(false);
        }
    };
    const handleAdminClick= async()=>{
        if(role!="ADMIN"){
            setError("Access Denied: You are not allowed to view Admin Dashboard");
            setAdminContent("");
            return;
        }
        setLoadingAdmin(true);
        setError("");
        try{
            const data= await fetchAdminContent();
            setAdminContent(data);
        }catch(err:any){
            setError(err.response?.data?.message || "Failed to fetch Admin Content");
        }finally{
            setLoadingAdmin(false);
        }
    };

    
    return(
        <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-start p-10">
            <div className="w-full flex justify-between items-center mb-6">
            <h1 className="text-3xl font-bold mb-6">Dashboard</h1>
            <button onClick={handleLogout} className="bg-red 600 text-white px-4 py-2 rounded-lg hover:bg-red-700 transition">
                Logout
            </button>
            </div>
            {error && <p className="text-red-500 mb-4">{error}</p>}

            <div className="flex gap-6">
                <div className="bg-white shadow-lg rounded-xl p-6 flex flex-col items-center w-64">
                <button onClick={handleUserClick} disabled={loadingUser} className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition mb-3 w-full">
                    {loadingUser? "Loading...":"User Dashboard"}
                </button>
                {userContent &&(<p className="text-gray-700 text-center mt-2">{userContent}</p>)}
                </div>

                <div className="bg-white shadow-lg rounded-xl p-6 flex flex-col items-center w-64">
                <button onClick={handleAdminClick} disabled={loadingAdmin} className="bg-blue-600 text-white px-4 py-2 rounded-lg hover:bg-blue-700 transition mb-3 w-full">
                    {loadingAdmin? "Loading...":"Admin Dashboard"}
                </button>
                {adminContent &&(<p className="text-gray-700 text-center mt-2">{adminContent}</p>)}
                </div>

            </div>
        </div>
    );
};

export default DashboardPage;