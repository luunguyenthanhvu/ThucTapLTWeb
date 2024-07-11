'use strict';

var cloudName = 'dter3mlpl';
var apiKey = '899244476586798';
var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

$('.ftco-animate.fadeInUp.ftco-animated .product .img-prod').each(
    (_, elements) => {
      const publicId = $(elements).data('assets');
      const imageUrl = cl.url(publicId);
      $(elements).find('img').prop('src', imageUrl);
    });

