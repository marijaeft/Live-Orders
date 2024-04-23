import { useState,useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import OrderService from './orderService/orderService'
import ShowMedia from './components/ShowMedia'
import ShowOrders from './components/ShowOrders'

function App() {
  const [orders, setOrders] = useState([]);
 const [showOrders,setShowOrders] = useState(false);
 const [showMedia,setShowMedia] = useState(false);

  useEffect(() => {
    fetchOrders();  
    const eventSource = OrderService.listenForOrderEvents();
    eventSource.addEventListener('message', (event) => {
            fetchOrders();
        })         
 
    eventSource.addEventListener('error', (error) => {
        console.error('Error occurred:', error);
    });

    return () => {
        eventSource.close();
    };
    
}, []);

useEffect (() =>{
  if(orders.length===0){
   <ShowMedia/>
   setShowMedia(true)
   setShowOrders(false)
  }
  else{
    <ShowOrders orders={orders}/>
    setShowMedia(false)
   setShowOrders(true)
  }
},[orders])
const fetchOrders = async () => {
  try {
      OrderService.fetchOrders().then(
          (response) => {
            setOrders(response.data);
            console.log(response.data)
          })
  } catch (error) {
      console.error('Error fetching orders:', error);
  }
};

  return (
  <div>
  <div className='container'>
  {showMedia && <ShowMedia/>}
  </div>
   <div className='container'>
  {showOrders && <ShowOrders orders={orders}/>}
  </div>
  </div>
  )
}

export default App
