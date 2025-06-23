import React from 'react';

// Gelecek bileşenler:
import Header from '../components/Header';
import CategoryScroll from '../components/CategoryScroll';
import SearchBar from '../components/SearchBar';
import Carousel from '../components/Carousel';
import Campaigns from '../components/Campaigns';
import CategorySectionList from '../components/CategorySectionList';

function MainPage() {
  return (
    <div className="flex flex-col gap-4 p-2">
      <Header />

      <CategoryScroll />

      <SearchBar />

      <Carousel />

      <Campaigns />

      <CategorySectionList />
    </div>
  );
}

export default MainPage;
