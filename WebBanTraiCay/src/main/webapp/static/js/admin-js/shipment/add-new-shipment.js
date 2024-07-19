'use strict';
let arrow = document.querySelectorAll(".arrow");
let closeSideBarBtn = document.querySelector(".btn-close-home");

for (var i = 0; i < arrow.length; i++) {
  arrow[i].addEventListener("click", (e) => {
    let arrowParent = e.target.parentElement.parentElement; // Trở về phần tử cha của mũi tên
    arrowParent.classList.toggle("showMenu");
  });
}

let sidebar = document.querySelector(".sidebar");
let sidebarBtn = document.querySelector(".bx-menu");

sidebarBtn.addEventListener("click", () => {
  sidebar.classList.toggle("close");

  // Sau khi toggle sidebar, kiểm tra và điều chỉnh hiển thị nút đóng sidebar
  if (!sidebar.classList.contains("close")) {
    closeSideBarBtn.style.display = "inline-block"; // Hiển thị nút đóng
  } else {
    closeSideBarBtn.style.display = "none"; // Ẩn nút đóng
  }
});

closeSideBarBtn.addEventListener("click", () => {
  sidebar.classList.toggle("close");
  closeSideBarBtn.style.display = "none"; // Luôn ẩn nút đóng khi click để đóng sidebar
});

var myVar;

function myFunction() {
  myVar = setTimeout(showPage, 800);
}

function showPage() {
  document.getElementById("loader").style.display = "none";
  document.getElementById("myDiv").style.display = "block";
}

$('.tab').on('click', function () {
  $('.tab').removeClass('active');
  $(this).addClass('active');
});
var dataResponse = [];
var tableAddNewShipment = new DataTable('#tableAddNewShipment', {
  searching: false,
  bDeferRender: true,
  ajax: {
    url: `${window.context}/api/shipments-api/list-item-shipments`,
    type: 'POST',
    data: function (d) {
      d.searchText = $('#product-name').val();
      return JSON.stringify(d);
    },
    dataSrc: function (response) {
      response.data.forEach(data => {
        let existingItem = dataResponse.find(item => item.id === data.id);
        if (!existingItem) {
          data.importPrice = 0;
          data.quantity = 0;
          dataResponse.push(data);
        } else {
          existingItem.quantityStock = data.quantityStock;
        }
      })
      return response.data;
    }
  },
  serverSide: true,
  processing: true,
  scrollY: '550px',
  scrollCollapse: true,
  columns: [{
    data: undefined, render: function (data, type, row) {
      return `<div class="image-table-product flex">  
                    <img src="${row.image}" data-assets="${row.imgPublicId}" src="${window.context}/static/images/loading-cat.gif"> 
                    <div class="product-name">
                        <span>${row.productName}</span>
                    </div>
                </div>`
    }, width: "25%"
  }, {
    data: undefined, render: function (data, type, row) {
      return `<div class="sku-code">  
                    <span>${row.id}</span>
                </div>`
    }, width: "8%"
  }, {
    data: undefined, render: function (data, type, row) {
      return `<div class="supplier">  
                   <span>${row.provider}</span>
                </div>`
    }, width: "15%"
  }, {
    data: undefined, render: function (data, type, row) {
      return `<div class="quantity-product">  
                    <span>${row.quantityStock}</span>
                </div>`
    }, width: "8%"
  }, {
    data: undefined, render: function (data, type, row) {
      var item = dataResponse.find(d => d.id === row.id);
      var value = item ? item.quantity : 0;
      return `<div style="width: 100px;" class="new-quantity-product">  
                    <input style="width: 100px" value="${value}" type="number" class="form-control" placeholder="Enter a number" min="0">
                </div>`
    }, width: "13%"
  }, {
    data: undefined, render: function (data, type, row) {
      var item = dataResponse.find(d => d.id === row.id);
      var value = item ? item.importPrice : 0;
      return `<div style="width: 100px;" class="price-product-in">  
                    <input value="${value}" style="width: 100px;" type="text">
                </div>`
    }, width: "5%"
  }, {
    data: undefined, render: function (data, type, row) {
      const select = $('.shipment-transaction-note').clone();
      select.css('display', 'block');
      return select.prop('outerHTML');
    }, width: "10%"
  }, {
    data: undefined, render: function (data, type, row) {
      return `<div class="actions">  
                          <button class="btn-hide" value="${row.id}"> 
                            <span><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M170.5 51.6L151.5 80h145l-19-28.4c-1.5-2.2-4-3.6-6.7-3.6H177.1c-2.7 0-5.2 1.3-6.7 3.6zm147-26.6L354.2 80H368h48 8c13.3 0 24 10.7 24 24s-10.7 24-24 24h-8V432c0 44.2-35.8 80-80 80H112c-44.2 0-80-35.8-80-80V128H24c-13.3 0-24-10.7-24-24S10.7 80 24 80h8H80 93.8l36.7-55.1C140.9 9.4 158.4 0 177.1 0h93.7c18.7 0 36.2 9.4 46.6 24.9zM80 128V432c0 17.7 14.3 32 32 32H336c17.7 0 32-14.3 32-32V128H80zm80 64V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16zm80 0V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16zm80 0V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16z"/></svg></span>
                          </button>
                      </div>`;
    }, width: "5%"
  },],
  drawCallback: function () {
    setTimeout(() => {
      console.log(tableAddNewShipment.rows())
      tableAddNewShipment.columns.adjust()
    }, 1500)
  }
})

$('#tableAddNewShipment').on('change', 'input[type="text"]', function () {
  let value = Number($(this).val()) || 0;
  if (!value || value < 0) {
    value = 0;
    $(this).val(0);
  }
  let trElement = $(this).closest('td').closest('tr');
  dataResponse.forEach(d => {
    if (d.id === parseInt(trElement.find('.sku-code span').text())) {
      d.importPrice = value;
    }
  })
})

$('#tableAddNewShipment').on('change', 'input[type="number"]', function () {
  let value = Number($(this).val()) || 0;

  if (!value || value < 0) {
    value = 0;
    $(this).val(0);
  }

  let trElement = $(this).closest('td').closest('tr');
  dataResponse.forEach(d => {
    if (d.id === parseInt(trElement.find('.sku-code span').text())) {
      d.quantity = value;
    }
  })
});

$('#tableAddNewShipment').on('click', '.btn-hide', function () {
  let value = $(this).val();
  Swal.fire({
    title: "Ban muon xoa khoi lo hang khong?",
    showDenyButton: true,
    showCancelButton: true,
    confirmButtonText: "YES!",
    denyButtonText: "NO!",
  }).then((result) => {
    console.log(result)
    console.log($(this).val());
    if (result.isConfirmed) {
      const swalLoading = Swal.fire({
        title: 'Loading...',
        text: 'Please wait while we process your request.',
        allowOutsideClick: false,
        didOpen: () => {
          Swal.showLoading();
        }
      });

      // Thực hiện yêu cầu AJAX
      $.ajax({
        url: `${window.context}/api/shipments-api/delete-item-shipments`,
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
          id: value,
        }),
        success: function (response) {
          setTimeout(() => {
            // Ẩn modal loading
            tableAddNewShipment.ajax.reload();
            dataResponse = dataResponse.filter(item => item.id !== value);
            swalLoading.close();
          }, 1000)
        },
        error: function (xhr, status, error) {
          // Ẩn modal loading
          swalLoading.close();

          // Hiển thị lỗi
          console.log(xhr);
          console.log(status);
          console.log(error);

          Swal.fire({
            title: 'Error!',
            text: 'Something went wrong. Please try again.',
            icon: 'error'
          });
        }
      });
    }
  })
})

tableAddNewShipment.on('draw.dt', function () {
  var cloudName = 'dter3mlpl';
  var apiKey = '899244476586798';
  var cl = cloudinary.Cloudinary.new({cloud_name: cloudName});

  $('tbody tr td .image-table-product.flex img').each((_, elements) => {
    const publicId = $(elements).data('assets');
    const imgUrl = cl.url(publicId);
    const imgDefault = `${window.context}/static/images/default-fruit.jpg`;
    // check if image is exits
    if (imgUrl !== null) {
      $(elements).prop('src', imgUrl);
    } else {
      $(elements).prop('src', imgDefault);
    }
  })
});

$('.search-btn').on('click', function () {
  tableAddNewShipment.ajax.reload();
})

$('.reset-btn').on('click', function () {
  $('#product-name').val('');
  tableAddNewShipment.ajax.reload();
})

$('#btn-add-shipment').on('click', function () {
  dataResponse.forEach(item => {
    if (item.importPrice === 0 || item.quantity === 0) {
      return Swal.fire({
        title: "Nhap thieu thong tin?",
        text: "Vui long nhap day du cac thong tin",
        icon: "question"
      });
    } else {
      Swal.fire({
        title: "Them lo hang",
        text: "Ban chac chan them lo hang nay?",
        showDenyButton: true,
        showCancelButton: true,
        confirmButtonText: "Co!",
        denyButtonText: "Khong!",
      }).then((result) => {
        console.log(result)
        console.log($(this).val())
        if (result.isConfirmed) {
          const swalLoading = Swal.fire({
            title: 'Loading...',
            text: 'Please wait while we process your request.',
            allowOutsideClick: false, // Ngăn người dùng đóng modal bằng cách nhấp ra ngoài
            didOpen: () => {
              Swal.showLoading(); // Hiển thị spinner loading
            }
          });
          const note = $('#note-text').val();
          $.ajax({
            url: `${window.context}/api/shipments-api/create-new-shipment`,
            type: 'POST',
            data: JSON.stringify({
              noteText: note,
              data: dataResponse
            }),
            success: function (response) {
              setTimeout(() => {
                // Ẩn modal loading
                swalLoading.close();

                // Chuyển hướng sau khi thành công
                window.location.href = `${window.context}/admin/product/product-list`;
              }, 1000)
            },
          })
        } else if (result.isDenied) {
        }
      });
    }
  })
})
