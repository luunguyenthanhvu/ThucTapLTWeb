'use strict';
function generateRandomInteger(N) {
  return Math.floor(Math.random() * N) + 1;
}
function generateRandomFloat(N) {
  return (Math.random() * N + 1.0).toFixed(1);
}
const loadProducts = async function() {
  await Promise.all($('.grib-product .product-show-wrap').get().map(async (element) => {
    const url = $(element).find('a[rel="nofollow"]').attr('href')
    const thumbnail = $(element).find('.thumbnail img').attr('src')
    const name = $(element).find('.product-info .product-name h3').text().trim();
    const price = $(element).find('.price .woocommerce-Price-amount').text().replace('đ', '').replaceAll('.', '').trim();
    const specification = $(element).find('.production-information .specifications').text().trim().toLowerCase().replace('quy cách : ', '').trim()
    let brand = null
    let description = null
    let blog = null
    let galleries = null
    await $.ajax({
      url: url,
      type: 'get',
      success: function(data) {
        brand = $(data).find('.thong-so-sp li:last-child .thong-tin-sp').text()
        description = $(data).find('.product-detail p').text()
        blog = $('<div id="blog-content"></div>')
        $(data).find('#pills-home').children().not('div').removeClass().get().forEach(e => blog.append(e))
        blog = blog.html()
        galleries = $(data).find('.item-small img').get().map(img => $(img).attr('src'))
      }
    })
    return {
      thumbnail: thumbnail,
      name: name,
      price: price,
      specification: specification,
      brand: brand,
      description: description,
      blog: blog,
      quantity: generateRandomInteger(200),
      status: 1,
      minAge: generateRandomInteger(10),
      weight: generateRandomFloat(5.8),
      discountId: generateRandomInteger(8),
      producerId: generateRandomInteger(8),
      categoryId: 9,
      galleries: galleries,
    }
  }))
  .then(result => console.log(result))
}

loadProducts()
