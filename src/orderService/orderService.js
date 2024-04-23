import axios from "../custom-axios/axios";

const OrderService = {

    fetchOrders : () => {
        return axios.get(`/orders`);
    },
    addOrder : () => {
        return axios.post(`/add/order`);
    },
    editOrder : (id, orderStatus) => {
        return axios.post(`/edit/order`, {
            "id" : id,
            "orderStatus" : orderStatus
        });
    },
    listenForOrderEvents: () => {
        const url = import.meta.env.VITE_ORDER_SERVICE_URL;
        return new EventSource(`http://${url}:9090/api/sse`);
    },
    // getVideo: () => {
    //     return axios.get("/video", { responseType: 'blob' });
    // },
    // getImage: () => {
    //     return axios.get("/image", { responseType: 'blob' });
    // },
    // getGif: () => {
    //     return axios.get("/gif", { responseType: 'blob' });
    // }
    getMedia: () => {
        return axios.get("/media", {responseType : 'blob'})
    }
}

export default OrderService;