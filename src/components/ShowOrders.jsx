

function ShowOrders (props){
    const inProgressOrders = props.orders.filter(order => order.orderStatus === "IN_PROGRESS")
    const readyForPickuopOrders = props.orders.filter(order => order.orderStatus === "READY_FOR_PICKUP")
    const recentlyDeleted= props.orders.filter(order => order.orderStatus === "FINISHED")

    return(
    <div className="container">
        <div className='row'>
       <div className='col text-start'><h1>Се подготвуваат</h1>
           <ul className='list-group order-num float-start'>
            <div className='row'>
              {inProgressOrders.map((order, index) => (
               <li key={order.id} className='list-item '>
                   <span> {order.orderNumber}</span>
               </li>
              ))}</div>
           </ul>
       </div>
       <div className=' col vl'></div>
       <div className='col text-end'><h1>За подигнување</h1>
            <ul className='list-group order-num float-end'>
              <div className='row'>
              {readyForPickuopOrders.map((order, index) => (
               <li key={order.id} className='list-item '>
                   <span> {order.orderNumber}</span>
               </li>
              ))}</div>
           </ul>
       </div>
       <div className=' col vl'></div>
       <div className='col text-end'><h1>Скоро избришани</h1>
            <ul className='list-group order-num float-end'>
              <div className='row'>
              {recentlyDeleted.map((order, index) => (
               <li key={order.id} className='list-item '>
                   <span> {order.orderNumber}</span>
               </li>
              ))}</div>
           </ul>
       </div>
    </div>
        
    </div>
    )
    }
    
export default ShowOrders