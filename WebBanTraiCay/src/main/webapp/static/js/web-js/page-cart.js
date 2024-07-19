'use strict';

var cloudName = 'dter3mlpl';
var apiKey = '899244476586798';
var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

$('#mainContent .text-center .image-prod .img').each(
    (_, elements) => {
      const publicId = $(elements).data('assets');
      const imageUrl = cl.url(publicId);
      const baseUrl = `${window.context}/static/images/loading-cat.gif"`;
      $(elements).find('img').prop('src', imageUrl);
      if (imageUrl) {
        $(elements).find('img').prop('src', imageUrl);
      } else {
        $(elements).find('img').prop('src', baseUrl);
      }
    });

function deleteProduct(productId) {
  Swal.fire({
    title: "Ban muon xoa san pham khoi gio hang?",
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: "Xoa",
    denyButtonText: `Khong xoa!`
  }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      Swal.fire("Saved!", "", "success");
      if (!socket || socket.readyState === WebSocket.CLOSED) {
        initializeWebSocket();
      }

      const sendMessage = () => {
        var message = {
          "id": productId,
          "quantity": 0,
          "action": "delete"
        };

        console.log(message)

        if (socket.readyState === WebSocket.OPEN) {
          socket.send(JSON.stringify(message));
          showToast("Success");
          console.log('Message sent:', message);
        } else {
          console.error('WebSocket is not open. Cannot send message.');
          setTimeout(sendMessage, 100);
        }
      };

      sendMessage();
    } else if (result.isDenied) {
      Swal.fire("Changes are not saved", "", "info");
    }
  });
}

$('.cart-total-amount').text("0");
// Global WebSocket instance
let socket = null;
initializeWebSocket();

function initializeWebSocket() {
  if (socket && socket.readyState !== WebSocket.CLOSED) {
    console.log('WebSocket connection already established.');
    return;
  }
  socket = new WebSocket('ws://localhost:8080/websocket/cart-socket');

  socket.onopen = () => {
    console.log('WebSocket connected');
  };

  socket.onmessage = (event) => {
    console.log(event.data)
    try {
      const data = JSON.parse(event.data);
      const cartItemListSize = data.cartItemList.length;
      renderCart(data);
      $('.cart-total-amount').text(cartItemListSize);
    } catch (e) {
      console.error('Failed to parse message', e);
    }
  };

  socket.onerror = (error) => {
    console.error('WebSocket error observed:', error);
  };

  socket.onclose = () => {
    console.log('WebSocket connection closed.');
    socket = null;
  };
}

function addProductToCart(productId) {
  if (!socket || socket.readyState === WebSocket.CLOSED) {
    initializeWebSocket();
  }

  const sendMessage = () => {
    var message = {
      "id": productId,
      "quantity": 1,
      "action": "add"
    };

    console.log(message)

    if (socket.readyState === WebSocket.OPEN) {
      socket.send(JSON.stringify(message));
      showToast("Success");
      console.log('Message sent:', message);
    } else {
      console.error('WebSocket is not open. Cannot send message.');
      setTimeout(sendMessage, 100);
    }
  };

  sendMessage();
}

function addProductToCartWithWeight(productId) {
  if (!socket || socket.readyState === WebSocket.CLOSED) {
    initializeWebSocket();
  }

  const sendMessage = () => {
    var message = {
      "id": productId,
      "quantity": $('#quantity').val(),
      "action": "add"
    };

    console.log(message)

    if (socket.readyState === WebSocket.OPEN) {
      socket.send(JSON.stringify(message));
      showToast("Success");
      console.log('Message sent:', message);
    } else {
      console.error('WebSocket is not open. Cannot send message.');
      setTimeout(sendMessage, 100);
    }
  };

  sendMessage();
}

function decreProduct(productId) {
  if (!socket || socket.readyState === WebSocket.CLOSED) {
    initializeWebSocket();
  }

  const sendMessage = () => {
    var message = {
      "id": productId,
      "quantity": -1,
      "action": "add"
    };

    console.log(message)

    if (socket.readyState === WebSocket.OPEN) {
      socket.send(JSON.stringify(message));
      showToast("Success");
      console.log('Message sent:', message);
    } else {
      console.error('WebSocket is not open. Cannot send message.');
      setTimeout(sendMessage, 100);
    }
  };

  sendMessage();
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
  } else if (response === "Product does not exist") {
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

function renderCartItem(item) {
  return ` <tr class="text-center">
            <td class="product-remove"><a
                    href="javascript:void(0);"
                    onclick="deleteProduct(${item.id})"><span
                    class="ion-ios-close"></span></a></td>
            <td><input name="selectedProducts"
                       value="${item.id}"
                       style="cursor: pointer; margin-top: 10px; width: 25px; height: 25px"
                       type="checkbox"></td>
            <td class="image-prod">
                <div class="img" data-assets="${item.imgPublicId}">
                    <img style="width: 100px; height: 100px; object-fit: cover"
                         class="img-fluid"
                         src="${window.context}/static/images/loading-cat.gif"
                         alt="Colorlib Template">
                </div>
            </td>
            <td class="product-name">
                <h3>${item.productName}</h3>
            </td>
            <td class="price">
                ${new Intl.NumberFormat('vi-VN',
      {style: 'currency', currency: 'VND'}).format(item.price)}
            </td>
            <td class="quantity">
                <div class="input-group mb-3">
                    <span class="input-group-btn mr-2">
                        <a href="javascript:void(0);"
                           onclick="decreProduct(${item.id})"
                           class="btn-plus-indre"
                           data-type="minus" data-field="">
                            <i class="ion-ios-remove"></i>
                        </a>
                    </span>
                    <input type="text" name="quantity"
                           class="form-control input-number"
                           value="${item.quantity}" min="1">
                    <span class="input-group-btn ml-2">
                        <a href="javascript:void(0);"
                           onclick="addProductToCart(${item.id})"
                           class="btn-plus-indre"
                           data-type="plus" data-field="">
                            <i class="ion-ios-add"></i>
                        </a>
                    </span>
                </div>
            </td>
            <td class="total">
                ${new Intl.NumberFormat('vi-VN',
      {style: 'currency', currency: 'VND'}).format(item.price * item.quantity)}
            </td>
        </tr>`;
}

function renderCart(cartData) {
  const cartItemsHtml = cartData.cartItemList.map(renderCartItem).join('');
  document.querySelector('#mainContent').innerHTML = cartItemsHtml;
  document.querySelector(
      '.cart-total-amount').textContent = cartData.cartItemList.length;
  $('#mainContent .text-center .image-prod .img').each(
      (_, elements) => {
        const publicId = $(elements).data('assets');
        const imageUrl = cl.url(publicId);
        const baseUrl = `${window.context}/static/images/loading-cat.gif"`;
        $(elements).find('img').prop('src', imageUrl);
        if (imageUrl) {
          $(elements).find('img').prop('src', imageUrl);
        } else {
          $(elements).find('img').prop('src', baseUrl);
        }
      });
}
