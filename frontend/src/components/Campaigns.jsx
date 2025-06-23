import React from 'react';
import { useNavigate } from 'react-router-dom';

const campaigns = [
  { id: 'yaz-indirimi', title: 'Yaz İndirimi' },
  { id: 'hafta-sonu-firsatlari', title: 'Hafta Sonu Fırsatları' },
];

function Campaigns() {
  const navigate = useNavigate();

  const handleClick = (campaignId) => {
    navigate(`/campaign/${campaignId}`);
  };

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
          {campaign.title}
        </div>
      ))}
    </div>
  );
}

export default Campaigns;
