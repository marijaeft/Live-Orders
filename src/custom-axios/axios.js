import axios from "axios"

const instance = axios.create({
    baseURL: `http://${import.meta.env.VITE_ORDER_SERVICE_URL}:9090/api`,
    headers: {
        'Access-Control-Allow-Origin' : '*',
    }
})
export default instance;