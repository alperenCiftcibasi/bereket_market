import React, { useEffect } from 'react';

// Bileşenler:
import Header from '../components/Header';
import CategoryScroll from '../components/CategoryScroll';
import SearchBar from '../components/SearchBar';
import Carousel from '../components/Carousel';
import Campaigns from '../components/Campaigns';
import CategorySectionList from '../components/CategorySectionList';

import { useCategory } from '../context/CategoryContext';

function MainPage() {
  const { findAllCategories } = useCategory();

  useEffect(() => {
    findAllCategories();
  }, [findAllCategories]);

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
