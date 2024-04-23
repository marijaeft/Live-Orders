import { useState,useEffect } from 'react'
import OrderService from '../orderService/orderService';

function ShowMedia (){
    const [showImage, setShowImage] = useState(false);
    const [showVideo, setShowVideo] = useState(false);
    const [imageUrl, setImageUrl] = useState('');
    const [videoUrl, setVideoUrl] = useState('');
    const [mediaUrl, setMediaUrl] = useState('');
    useEffect (()=>{
            OrderService.getMedia()
      .then(response => {
        const mediaType = response.headers['content-type'];
        const mediaUrl = URL.createObjectURL(response.data)
        console.log(response.data)
        if (mediaType.startsWith('image')) {
          setShowImage(true);
          setImageUrl(mediaUrl);
        } else if (mediaType.startsWith('video')) {
          setShowVideo(true);
          setVideoUrl(mediaUrl);
        } else {
          console.error('Unsupported media type:', mediaType);
        }
      })
      .catch(error => {
        console.error('Error fetching media:', error);
      });
    },[])
return(
    <div className="container">
    {showImage && imageUrl && (
        <img src={imageUrl} alt="Fetched Image" />
    )}
    {showVideo && videoUrl && (
        <video controls autoPlay muted loop>
            <source src={videoUrl} type="video/mp4" />
            Your browser does not support the video tag.
        </video>
    )}
</div>
)
}

export default ShowMedia