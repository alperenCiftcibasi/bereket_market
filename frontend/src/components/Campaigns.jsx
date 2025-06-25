import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from '../api/axiosInstance';

function Campaigns() {
  const [campaigns, setCampaigns] = useState(null);
  const navigate = useNavigate();

  const fetchCampaigns = async () => {
    try {
      const response = await axios.get('/api/admin/campaigns/get'); // Endpointi kontrol et
      setCampaigns(response.data);
    } catch (error) {
      console.error('Kampanya çekme hatası:', error);
      setCampaigns([]);
    }
  };

  useEffect(() => {
    fetchCampaigns(); // İlk yüklemede çağır

    // 10 saniyede bir kampanyaları güncelle
    const intervalId = setInterval(() => {
      fetchCampaigns();
    }, 10000);

    // Cleanup: component unmount olduğunda interval'ı temizle
    return () => clearInterval(intervalId);
  }, []);

  const handleClick = (campaignId) => {
    navigate(`/campaign/${campaignId}`);
  };

  if (campaigns === null) {
    return <p style={{ padding: '1rem' }}>Kampanyalar yükleniyor...</p>;
  }

  if (campaigns.length === 0) {
    return <p style={{ padding: '1rem' }}>Aktif kampanya bulunmamaktadır.</p>;
  }

  return (
    <div style={{
      display: 'grid',
      gridTemplateColumns: 'repeat(auto-fit, minmax(150px, 1fr))',
      gap: '1rem',
      padding: '1rem'
    }}>
      {campaigns.map(campaign => (
        <div
          key={campaign.id}
          onClick={() => handleClick(campaign.id)}
          style={{
            backgroundColor: '#ffe0e0',
            padding: '1rem',
            borderRadius: '10px',
            boxShadow: '0 2px 5px rgba(0,0,0,0.1)',
            cursor: 'pointer',
            textAlign: 'center',
            fontWeight: 'bold'
          }}
        >
          {campaign.name}
        </div>
      ))}
    </div>
  );
}

export default Campaigns;
