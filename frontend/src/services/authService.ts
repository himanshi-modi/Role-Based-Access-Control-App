import axios from "axios";


const APP_URL="http://localhost:8080/api";

export interface LoginResponse{
    jwt:string;
    id:number;
}

export const loginUser=async(email:string,password:string)=>{
    const response=await axios.post<LoginResponse>(`${APP_URL}/auth/login`,{email,password});
    return response.data;
};

export interface SignupResponse{
    name:string;
    email:string;
    password:string;
}

export const signupUser=async(name:string, email:string,password:string)=>{
    const response=await axios.post<SignupResponse>(`${APP_URL}/auth/signup`,{name,email,password});
    return response.data;
};


