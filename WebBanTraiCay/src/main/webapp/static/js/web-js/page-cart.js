// delete product
function deleteProduct(productId) {
  var isRemoved = confirm('Bạn có muốn xóa sản phẩm ra khỏi giỏ hàng không?');
  if(isRemoved) {
    $.ajax({
      type: 'POST',
      url: `${window.context}/page/cart/remove-product-cart`,
      data: {
        productId: productId
      },
      success: function (response) {
        showToast(response.message);
        reloadCartProduct();
        if(response.message === "Removed from cart") {
          updateCartAmount();
        }
      },
      error: function (error) {
        console.log(error);
      }
    })
  }
}
// inc product
function incProduct(productId) {
  $.ajax({
    type: 'POST',
    url: `${window.context}/page/cart/quantity-inc-dec`,
    data: {
      productId: productId,
      actionType: 'inc'
    },
    success: function (response) {
      showToast(response.message);
      reloadCartProduct();
    },
    error: function (error) {
      alert(error);
    }
  })
}

// dec product
function decProduct(productId) {
  $.ajax({
    type: 'POST',
    url: `${window.context}/page/cart/quantity-inc-dec`,
    data: {
      productId: productId,
      actionType: 'dec'
    },
    success: function (response) {
      showToast(response.message);
      reloadCartProduct();
      if(response.message === "Removed from cart") {
        updateCartAmount();
      }
    },
    error: function (error) {
      alert(error);
    }
  })
}

// reload page with new amount
function reloadCartProduct() {
  $.ajax({
    type: 'POST',
    url: `${window.context}/page/cart`,
    data: {},
    success: function (updateHTML) {
      $('#mainContent').html(updateHTML);
    },
    error: function (error) {
      console.log(error);
    }

  });
}

// toast function
function toast({
  title = '',
  message = '',
  type = '',
  duration = 3000
}) {
  const main = document.getElementById('toast');
  if (main) {
    const toast = document.createElement('div');

    // auto remove toast
    const autoRemoveID = setTimeout(function () {
      main.removeChild(toast);
    }, duration)

    // remove when click
    toast.onclick = function (e) {
      if (e.target.closest('.toast__close')) {
        main.removeChild(toast);
        clearTimeout(autoRemoveID);
      }
    }

    const icons = {
      success: '<svg xmlns="http://www.w3.org/2000/svg" height="16" width="16" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM369 209L241 337c-9.4 9.4-24.6 9.4-33.9 0l-64-64c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l47 47L335 175c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9z"/></svg>',
      info: '<svg xmlns="http://www.w3.org/2000/svg" height="16" width="16" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zM216 336h24V272H216c-13.3 0-24-10.7-24-24s10.7-24 24-24h48c13.3 0 24 10.7 24 24v88h8c13.3 0 24 10.7 24 24s-10.7 24-24 24H216c-13.3 0-24-10.7-24-24s10.7-24 24-24zm40-208a32 32 0 1 1 0 64 32 32 0 1 1 0-64z"/></svg>',
      warning: '<svg xmlns="http://www.w3.org/2000/svg" height="16" width="16" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M256 32c14.2 0 27.3 7.5 34.5 19.8l216 368c7.3 12.4 7.3 27.7 .2 40.1S486.3 480 472 480H40c-14.3 0-27.6-7.7-34.7-20.1s-7-27.8 .2-40.1l216-368C228.7 39.5 241.8 32 256 32zm0 128c-13.3 0-24 10.7-24 24V296c0 13.3 10.7 24 24 24s24-10.7 24-24V184c0-13.3-10.7-24-24-24zm32 224a32 32 0 1 0 -64 0 32 32 0 1 0 64 0z"/></svg>',
      error: '<svg xmlns="http://www.w3.org/2000/svg" height="16" width="16" viewBox="0 0 512 512"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M256 512A256 256 0 1 0 256 0a256 256 0 1 0 0 512zm0-384c13.3 0 24 10.7 24 24V264c0 13.3-10.7 24-24 24s-24-10.7-24-24V152c0-13.3 10.7-24 24-24zM224 352a32 32 0 1 1 64 0 32 32 0 1 1 -64 0z"/></svg>'
    };

    const icon = icons[type];
    const delay = (duration / 1000).toFixed(2);

    toast.classList.add('toast', `toast--${type}`);

    toast.innerHTML = `
                    <div class="toast__icon">
                        ${icon}
                    </div>

                    <div class="toast__body">
                    <h3 class="toast__title">
                        ${title}
                    </h3>
                    <p class="toast__msg">
                        ${message}
                    </p>
                    </div>

                    <div class="toast__close">
                    <svg xmlns="http://www.w3.org/2000/svg" height="16" width="12" viewBox="0 0 384 512">
                        <path
                            d="M342.6 150.6c12.5-12.5 12.5-32.8 0-45.3s-32.8-12.5-45.3 0L192 210.7 86.6 105.4c-12.5-12.5-32.8-12.5-45.3 0s-12.5 32.8 0 45.3L146.7 256 41.4 361.4c-12.5 12.5-12.5 32.8 0 45.3s32.8 12.5 45.3 0L192 301.3 297.4 406.6c12.5 12.5 32.8 12.5 45.3 0s12.5-32.8 0-45.3L237.3 256 342.6 150.6z"/>
                    </svg>
                    </div>
                `;
    main.appendChild(toast);
  }
}

function showToast(response) {
  if (response === "Out of quantity") {
    toast({
      title: 'Tối đa sản phẩm',
      message: 'Giỏ hàng không thể vượt quá tối đa sản phẩm tồn kho!',
      type: 'info',
      duration: 3000
    })
  } else if (response === "Removed from cart") {
    toast({
      title: 'Xóa sản phẩm',
      message: 'Đã xóa sản phẩm khỏi giỏ hàng!',
      type: 'info',
      duration: 3000
    })
  }else if (response === "Product does not exist") {
    toast({
      title: 'Lỗi',
      message: 'Sản phẩm không tồn tại!',
      type: 'error',
      duration: 3000
    })
  }
}

// update cart Amount
function updateCartAmount() {
  $.ajax({
    type: 'GET',
    url: '/page/cart/get-cart-amount',
    data: {},
    success: function (response) {
      $(".cart-total-amount").html(response);
    },
    error: function (error) {
      // Xử lý khi có lỗi
      console.log('Đã xảy ra lỗi khi thêm vào giỏ hàng');
    }
  })
}
