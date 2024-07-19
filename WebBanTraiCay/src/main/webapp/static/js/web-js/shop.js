'use strict';
$('#product-name').val(seachText);
$('#product-category').val(category);

function handleSearchAndPagination(pageId) {
  const category = $('#product-category').val();
  const productName = $('#product-name').val();
  const encodedCategory = encodeURIComponent(category);
  const encodedProductName = encodeURIComponent(productName);

  const url = `${window.location.origin}${window.context}/page/shop/shop-forward?pageId=${pageId}&category=${encodedCategory}&productName=${encodedProductName}`;
  window.location.href = url;
}

// Event listener for search button
$('#search-btn-category').on('click', function () {
  handleSearchAndPagination(1); // Always navigate to page 1 after search
});

// Event listener for reset button
$('#reset-btn-category').on('click', function () {
  $('#product-name').val('');
  $('#product-category').prop('selectedIndex', 0);
  handleSearchAndPagination(1); // Reset and navigate to page 1
});
