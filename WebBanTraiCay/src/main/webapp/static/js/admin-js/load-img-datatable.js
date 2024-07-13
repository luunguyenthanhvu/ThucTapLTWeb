'use strict';

var cloudName = 'dter3mlpl';
var apiKey = '899244476586798';
var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

$('tbody tr td .image-table-product.flex img').each((_, elements) => {
  const publicId = $(elements).data('assets');
  const imgUrl = cl.url(publicId);
  $(elements).prop('src', imgUrl);
})
