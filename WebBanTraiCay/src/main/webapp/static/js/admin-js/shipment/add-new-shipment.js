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

var tableAddNewShipment = new DataTable('#table-add-shipment', {
  searching: false,
  bDeferRender: true,
  searchDelay: 500,
  ajax: 'product-list.json',
  serverSide: true,
  processing: true,

  columns: [
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="image-table-product flex">  
                    <img src="${row.image}"> 
                    <div class="product-name">
                        <span>${row.productName}</span>
                    </div>
                </div>`
      },
      width: "20%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="sku-code">  
                    <span>${row.productCode}</span>
                </div>`
      },
      width: "5%"
    }, {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="supplier">  
                   <span>${row.supplier}</span>
                </div>`
      },
      width: "35%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="quantity-product">  
                    <span>${row.stockQuantity}</span>
                </div>`
      },
      width: "5%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="new-quantity-product">  
                    <input type="text">
                </div>`
      },
      width: "1%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<select class="shipment-transaction-note" name="shipment-transaction-note">
                  <option value="add-new-product">Thêm lô hàng mới</option>
                  <option value="out-date">Sản phẩm hết hạn</option>
                  <option value="add-more">Thêm sản phẩm</option>
                </select>`
      },
      width: "10%"
    },
    {
      data: undefined,
      render: function (data, type, row) {
        return `<div class="actions">  
                          <button class="btn-hide"> 
                            <span><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512"><!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d="M170.5 51.6L151.5 80h145l-19-28.4c-1.5-2.2-4-3.6-6.7-3.6H177.1c-2.7 0-5.2 1.3-6.7 3.6zm147-26.6L354.2 80H368h48 8c13.3 0 24 10.7 24 24s-10.7 24-24 24h-8V432c0 44.2-35.8 80-80 80H112c-44.2 0-80-35.8-80-80V128H24c-13.3 0-24-10.7-24-24S10.7 80 24 80h8H80 93.8l36.7-55.1C140.9 9.4 158.4 0 177.1 0h93.7c18.7 0 36.2 9.4 46.6 24.9zM80 128V432c0 17.7 14.3 32 32 32H336c17.7 0 32-14.3 32-32V128H80zm80 64V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16zm80 0V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16zm80 0V400c0 8.8-7.2 16-16 16s-16-7.2-16-16V192c0-8.8 7.2-16 16-16s16 7.2 16 16z"/></svg></span>
                          </button>
                      </div>`;
      },
      width: "5%"
    },
  ],
  "initComplete": function () {
    console.log("cc")
  }
})
