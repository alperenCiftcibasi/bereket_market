import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import Header from '../components/Header';

function CampaignPage() {
  const { campaignId } = useParams();
  const navigate = useNavigate();

  // Kampanya başlıkları örnek olarak
  const titles = {
    'yaz-indirimi': 'Yaz İndirimi',
    'hafta-sonu-firsatlari': 'Hafta Sonu Fırsatları'
  };

  const title = titles[campaignId] || 'Bilinmeyen Kampanya';

  return (
    <>
      <Header />
      <div style={{ padding: '2rem' }}>
        <h1>{title}</h1>
        <p>Bu sayfa {title} kampanyasına ait ürünleri gösterecek.</p>
        <button onClick={() => navigate(-1)}>Geri Dön</button>
      </div>
    </>
  );
}

export default CampaignPage;
