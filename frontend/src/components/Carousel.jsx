import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';

const images = [
  'https://picsum.photos/800/300?random=1',
  'https://picsum.photos/800/300?random=2',
  'https://picsum.photos/800/300?random=3',
];

function Carousel() {
  return (
    <div style={{ padding: '1rem' }}>
      <Swiper spaceBetween={30} slidesPerView={1} loop autoplay={{ delay: 3000 }}>
        {images.map((src, index) => (
          <SwiperSlide key={index}>
            <img src={src} alt={`slide-${index}`} style={{ width: '100%', borderRadius: '10px' }} />
          </SwiperSlide>
        ))}
      </Swiper>
    </div>
  );
}

export default Carousel;
