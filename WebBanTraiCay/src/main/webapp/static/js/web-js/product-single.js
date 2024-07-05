'use strict';
var galleryThumbs = new Swiper('.gallery-thumbs', {
  spaceBetween: 10,
  slidesPerView: 4,
  freeMode: true,
  watchSlidesProgress: true,
});
var galleryTop = new Swiper('.gallery-top', {
  spaceBetween: 10,
  navigation: {
    nextEl: '.swiper-button-next',
    prevEl: '.swiper-button-prev',
  },
  thumbs: {
    swiper: galleryThumbs
  }
});

var cloudName = 'dter3mlpl';
var apiKey = '899244476586798';
var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

$('.ftco-animate .swiper .swiper-wrapper .swiper-slide').each(
    (_, elements) => {
      const publicId = $(elements).data('assets');
      const imageUrl = cl.url(publicId);
      $(elements).css('background-image', `url(${imageUrl})`);
    });
