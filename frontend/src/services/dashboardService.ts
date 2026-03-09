import axios from "axios";

const API_URL="http://localhost:8080/api";

const getJwtToken=()=> localStorage.getItem("jwt");

export const fetchUserContent=async()=>{
    const jwt=getJwtToken();
    const response=await axios.get(`${API_URL}/user`,{
        headers:{ Authorization:`Bearer ${jwt}`},
    });
    return response.data;
};

export const fetchAdminContent=async()=>{
    const jwt=getJwtToken();
    const response=await axios.get(`${API_URL}/user`,{
        headers:{Authorization: `Bearer ${jwt}` },
    });
    return response.data;
};
