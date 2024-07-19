const urlFee = 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee';
const urlProvince='https://online-gateway.ghn.vn/shiip/public-api/master-data/province';
const urlDistrict='https://online-gateway.ghn.vn/shiip/public-api/master-data/district';
const urlServiceDelivery='https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/available-services';
const idProvinceSelected =0;
const headers = {
    'Content-Type': 'application/json', // Đảm bảo header Content-Type
    'token': 'b3d752b0-3b38-11ef-89ca-1aad91406dac',
    'shop_id': '5178578'
};

const body = {
    "service_type_id": 2,
    "insurance_value": 500000,
    "coupon": null,
    "from_district_id": 3695,
    "to_district_id": 1452, // Khởi tạo với giá trị mặc định
    "weight": 10000,
    "width": 15
};
const bodyIdProvince = {
    "province_id ":0
}
const selectProvinceElement = document.getElementById('provinces');
const selectElement = document.getElementById('districts');
let priceBuyFruit = document.querySelector('.tong_phu').textContent;
priceBuyFruit = priceBuyFruit.replace(/[,.₫]/g, "");
priceBuyFruit = parseInt(priceBuyFruit,10);

async function loadProvince() {
    try {
        const response = await fetch(urlProvince, {
            method: 'GET',
            headers: headers
        });
        if(!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }
        const data = await response.json()

        updateSelector(data.data)

    }catch (error) {
        console.error('Error:', error);
    }
}

loadProvince();
function updateSelector(data) {
    const selector = document.getElementById('provinces');
    // Xóa các option cũ trong select
    selector.innerHTML = '';

    // Sắp xếp mảng data theo tên tỉnh
    data.sort((a, b) => a.ProvinceName.localeCompare(b.ProvinceName));

    // Tạo input ẩn để lưu ProvinceName
    let hiddenInput = document.getElementById('hiddenProvinceName');
    if (!hiddenInput) {
        hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'provinceName';
        hiddenInput.id = 'hiddenProvinceName';
        selector.parentElement.appendChild(hiddenInput);
    }

    // Duyệt qua mảng data đã được sắp xếp và thêm từng option vào select
    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.ProvinceID;
        option.textContent = item.ProvinceName;
        option.setAttribute('data-province-name', item.ProvinceName); // Lưu ProvinceName vào data attribute
        selector.appendChild(option);
    });

    // Lắng nghe sự kiện thay đổi của select để cập nhật input ẩn
    selector.addEventListener('change', function() {
        const selectedOption = selector.options[selector.selectedIndex];
        hiddenInput.value = selectedOption.getAttribute('data-province-name');
    });
    // Kích hoạt sự kiện thay đổi để cập nhật giá trị ban đầu của input ẩn
    selector.dispatchEvent(new Event('change'));
}

let selectedProvince = document.getElementById('provinces').value;

// Bắt sự kiện khi người dùng click chọn tỉnh, thành phố
selectProvinceElement.addEventListener('change',async function() {
    bodyIdProvince.province_id= parseInt(selectProvinceElement.value, 10);
    try{
        const response = await fetch(urlDistrict, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(bodyIdProvince)
        });
        if(!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json()
        updateSelectorDistrict(data.data)

    }catch (error) {
        console.error('Error:', error);
    }
})
function updateSelectorDistrict(data) {

    selectElement.innerHTML = '';

    // Sắp xếp mảng data theo tên quận
    data.sort((a, b) => a.DistrictName.localeCompare(b.DistrictName));

    // Tạo input ẩn để lưu DistrictName
    let hiddenInput = document.getElementById('hiddenDistrictName');
    if (!hiddenInput) {
        hiddenInput = document.createElement('input');
        hiddenInput.type = 'hidden';
        hiddenInput.name = 'districtName';
        hiddenInput.id = 'hiddenDistrictName';
        selectElement.parentElement.appendChild(hiddenInput);
    }

    // Duyệt qua mảng data và thêm từng option vào select
    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.DistrictID;
        option.textContent = item.DistrictName;
        option.setAttribute('data-district-name', item.DistrictName);
        selectElement.appendChild(option);
    });

    // Lắng nghe sự kiện thay đổi của select để cập nhật input ẩn
    selectElement.addEventListener('change', function() {
        const selectedOption = selectElement.options[selectElement.selectedIndex];
        hiddenInput.value = selectedOption.getAttribute('data-district-name');
    });
    // Kích hoạt sự kiện thay đổi để cập nhật giá trị ban đầu của input ẩn
    selectElement.dispatchEvent(new Event('change'));
}

// Bắt sự kiện khi người dùng click chọn quận
selectElement.addEventListener('change', async function() {
    // Update the body with the selected district and ensure it's an integer
    body.to_district_id = parseInt(selectElement.value, 10);
    body.insurance_value = priceBuyFruit;

    try {
        const response = await fetch(urlFee, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(body)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        console.log(data); // Handle the response data here
        document.getElementById("delivery_fee").innerHTML = data.data.service_fee.toLocaleString('vi-VN') + ' ₫';
        document.getElementById("delivery_fee").value = data.data.service_fee;

        var tongPhu = document.querySelector('.tong_phu').textContent;
        tongPhu = parseInt(tongPhu.replace(/[^\d]/g, ''), 10);
        var tienVanChuyen=data.data.service_fee;
        document.getElementById("hidden_delivery_fee").value=tienVanChuyen;
        var tongCong = tongPhu + tienVanChuyen;
        document.querySelector('.tong_cong').innerHTML = tongCong.toLocaleString('vi-VN') + ' ₫';

    } catch (error) {
        console.error('Error:', error);
    }
});

// Js xử lý cho Popup Voucher

    var openBtn = document.getElementById('openBtnVoucher');
    var closeBtn = document.getElementById('closeBtn');
    var popup = document.getElementById('popup');
    var applyBtn = document.getElementById('applyBtnVoucher');
    var couponsList = document.getElementById('couponsList');

openBtn.addEventListener('click', async function () {
    popup.style.display = 'flex';
    await fetchCoupons(); // Chờ cho hàm fetchCoupons() hoàn thành

    var couponsList = document.getElementById('couponsList');
    var coupons = couponsList.querySelectorAll('.coupon');

    let tongTienSauKhiXaiVoucher = 0;

    for (let i = 0; i < coupons.length; i++) {
        coupons[i].addEventListener('click', function () {
            var tongTienBanDau = document.querySelector('.tong_cong').textContent;
            tongTienBanDau = parseInt(tongTienBanDau.replace(/[^\d]/g, ''), 10);
            const tongTien = tongTienBanDau;
            document.querySelector('#idVoucher').value = this.querySelector('input[type="radio"]').value;

            if (this.querySelector('input[type="radio"]').checked) {
                var tongTienTemp =tongTien;
                var tienGiam = coupons[i].value; // Sử dụng i để lấy giá trị tương ứng
                if (tienGiam < 1) {
                    tongTienTemp = tongTienTemp - tongTienBanDau * tienGiam;
                    tongTienSauKhiXaiVoucher= tongTienTemp;
                    document.querySelector('#discountVoucherPrice').innerHTML = (tienGiam * 100).toLocaleString('vi-VN') + ' %';
                } else {
                    tongTienTemp = tongTienTemp - tienGiam;
                    tongTienSauKhiXaiVoucher= tongTienTemp;
                    document.querySelector('#discountVoucherPrice').innerHTML = (tienGiam).toLocaleString('vi-VN') + ' đ';
                }
                document.querySelector('#priceAfterUseVoucher').innerHTML = tongTienTemp.toLocaleString('vi-VN') + ' ₫';
                tongTienTemp = tongTien;
            }
        });
    }

    document.querySelector('#confirmBtnVoucher').addEventListener('click', function () {
        popup.style.display = 'none';
        document.querySelector('.tong_cong').innerHTML = tongTienSauKhiXaiVoucher.toLocaleString('vi-VN') + ' ₫';
    });

});

// Js xử lý cho Popup Voucher

    var openBtn = document.getElementById('openBtnVoucher');
    var closeBtn = document.getElementById('closeBtn');
    var popup = document.getElementById('popup');
    var applyBtn = document.getElementById('applyBtnVoucher');
    var couponsList = document.getElementById('couponsList');

openBtn.addEventListener('click', async function () {
    popup.style.display = 'flex';
    await fetchCoupons(); // Chờ cho hàm fetchCoupons() hoàn thành

    var couponsList = document.getElementById('couponsList');
    var coupons = couponsList.querySelectorAll('.coupon');

    let tongTienSauKhiXaiVoucher = 0;

    for (let i = 0; i < coupons.length; i++) {
        coupons[i].addEventListener('click', function () {
            var tongTienBanDau = document.querySelector('.tong_cong').textContent;
            tongTienBanDau = parseInt(tongTienBanDau.replace(/[^\d]/g, ''), 10);
            const tongTien = tongTienBanDau;
            document.querySelector('#idVoucher').value = this.querySelector('input[type="radio"]').value;

            if (this.querySelector('input[type="radio"]').checked) {
                var tongTienTemp =tongTien;
                var tienGiam = coupons[i].value; // Sử dụng i để lấy giá trị tương ứng
                if (tienGiam < 1) {
                    tongTienTemp = tongTienTemp - tongTienBanDau * tienGiam;
                    tongTienSauKhiXaiVoucher= tongTienTemp;
                    document.querySelector('#discountVoucherPrice').innerHTML = (tienGiam * 100).toLocaleString('vi-VN') + ' %';
                } else {
                    tongTienTemp = tongTienTemp - tienGiam;
                    tongTienSauKhiXaiVoucher= tongTienTemp;
                    document.querySelector('#discountVoucherPrice').innerHTML = (tienGiam).toLocaleString('vi-VN') + ' đ';
                }
                document.querySelector('#priceAfterUseVoucher').innerHTML = tongTienTemp.toLocaleString('vi-VN') + ' ₫';
                tongTienTemp = tongTien;
            }
        });
    }

    document.querySelector('#confirmBtnVoucher').addEventListener('click', function () {
        popup.style.display = 'none';
        document.querySelector('.tong_cong').innerHTML = tongTienSauKhiXaiVoucher.toLocaleString('vi-VN') + ' ₫';
    });

});

// Js xử lý cho Popup Voucher

    var openBtn = document.getElementById('openBtnVoucher');
    var closeBtn = document.getElementById('closeBtn');
    var popup = document.getElementById('popup');
    var applyBtn = document.getElementById('applyBtnVoucher');
    var couponsList = document.getElementById('couponsList');

openBtn.addEventListener('click', async function () {
    popup.style.display = 'flex';
    await fetchCoupons(); // Chờ cho hàm fetchCoupons() hoàn thành

    var couponsList = document.getElementById('couponsList');
    var coupons = couponsList.querySelectorAll('.coupon');

    let tongTienSauKhiXaiVoucher = 0;

    for (let i = 0; i < coupons.length; i++) {
        coupons[i].addEventListener('click', function () {
            var tongTienBanDau = document.querySelector('.tong_cong').textContent;
            tongTienBanDau = parseInt(tongTienBanDau.replace(/[^\d]/g, ''), 10);
            const tongTien = tongTienBanDau;
            document.querySelector('#idVoucher').value = this.querySelector('input[type="radio"]').value;

            if (this.querySelector('input[type="radio"]').checked) {
                var tongTienTemp =tongTien;
                var tienGiam = coupons[i].value; // Sử dụng i để lấy giá trị tương ứng
                if (tienGiam < 1) {
                    tongTienTemp = tongTienTemp - tongTienBanDau * tienGiam;
                    tongTienSauKhiXaiVoucher= tongTienTemp;
                    document.querySelector('#discountVoucherPrice').innerHTML = (tienGiam * 100).toLocaleString('vi-VN') + ' %';
                }
                else {
                    tongTienTemp = tongTienTemp - tienGiam;
                    tongTienSauKhiXaiVoucher= tongTienTemp;
                    document.querySelector('#discountVoucherPrice').innerHTML = (tienGiam).toLocaleString('vi-VN') + ' đ';
                }
                document.querySelector('#priceAfterUseVoucher').innerHTML = tongTienTemp.toLocaleString('vi-VN') + ' ₫';
                tongTienTemp = tongTien;
            }
        });
    }

    document.querySelector('#confirmBtnVoucher').addEventListener('click', function () {
        popup.style.display = 'none';
        document.querySelector('.tong_cong').innerHTML = tongTienSauKhiXaiVoucher.toLocaleString('vi-VN') + ' ₫';
    });

});




closeBtn.addEventListener('click', function () {
        popup.style.display = 'none';
    });

    window.addEventListener('click', function (event) {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });

    async function fetchCoupons() {
        try{
            const response = await fetch("http://localhost:8080/voucher/get-voucher", {
                method: 'GET',

            });
            if(!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            console.log(data);
            renderCoupons(data)


        }catch (error) {
            console.error('Error:', error);
        }
    }

function renderCoupons(coupons) {
    couponsList.innerHTML = ''; // Xóa các mã giảm giá trước đó

    coupons.forEach(function (coupon) {
        // Tạo container cho từng mã giảm giá
        var couponDiv = document.createElement('div');
        couponDiv.className = 'coupon';

        // Tạo đối tượng SVG ảnh
        var couponSVG = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        couponSVG.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
        couponSVG.setAttribute('viewBox', '0 0 512 512');
        couponSVG.innerHTML = '<path d="M190.5 68.8L225.3 128H224 152c-22.1 0-40-17.9-40-40s17.9-40 40-40h2.2c14.9 0 28.8 7.9 36.3 20.8zM64 88c0 14.4 3.5 28 9.6 40H32c-17.7 0-32 14.3-32 32v64c0 17.7 14.3 32 32 32H480c17.7 0 32-14.3 32-32V160c0-17.7-14.3-32-32-32H438.4c6.1-12 9.6-25.6 9.6-40c0-48.6-39.4-88-88-88h-2.2c-31.9 0-61.5 16.9-77.7 44.4L256 85.5l-24.1-41C215.7 16.9 186.1 0 154.2 0H152C103.4 0 64 39.4 64 88zm336 0c0 22.1-17.9 40-40 40H288h-1.3l34.8-59.2C329.1 55.9 342.9 48 357.8 48H360c22.1 0 40 17.9 40 40zM32 288V464c0 26.5 21.5 48 48 48H224V288H32zM288 512H432c26.5 0 48-21.5 48-48V288H288V512z"/>';

        // Tạo container cho nội dung của coupon
        var couponContentDiv = document.createElement('div');
        couponContentDiv.className = 'coupon-content';

        var couponTitle = document.createElement('div');
        couponTitle.className = 'title';
        couponTitle.textContent = coupon.title;
        var couponContent = document.createElement('div');
        couponContent.textContent = coupon.content;
        var couponExpiry = document.createElement('div');
        couponExpiry.className = 'expiry';
        couponExpiry.textContent = `Hạn sử dụng: ${coupon.beginDate} - ${coupon.endDate}`;

        couponContentDiv.appendChild(couponTitle);
        couponContentDiv.appendChild(couponContent);
        couponContentDiv.appendChild(couponExpiry);

        // Tạo nút radio
        var couponInput = document.createElement('input');
        couponInput.type = 'radio';
        couponInput.name = 'coupon';
        couponInput.id = 'coupon' + coupon.id;
        couponInput.value=coupon.id;

        // Đưa các phần tử vào trong container của mã giảm giá
        couponDiv.appendChild(couponSVG);        // Cột 1
        couponDiv.appendChild(couponContentDiv); // Cột 2
        couponDiv.appendChild(couponInput);      // Cột 3
        couponDiv.value= coupon.price;

        // Đưa container của mã giảm giá vào danh sách mã giảm giá
        couponsList.appendChild(couponDiv);
    });
}







closeBtn.addEventListener('click', function () {
        popup.style.display = 'none';
    });

    window.addEventListener('click', function (event) {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });

    async function fetchCoupons() {
        try{
            const response = await fetch("http://localhost:8080/voucher/get-voucher", {
                method: 'GET',

            });
            if(!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            console.log(data);
            renderCoupons(data)


        }catch (error) {
            console.error('Error:', error);
        }
    }

function renderCoupons(coupons) {
    couponsList.innerHTML = ''; // Xóa các mã giảm giá trước đó

    coupons.forEach(function (coupon) {
        // Tạo container cho từng mã giảm giá
        var couponDiv = document.createElement('div');
        couponDiv.className = 'coupon';

        // Tạo đối tượng SVG ảnh
        var couponSVG = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        couponSVG.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
        couponSVG.setAttribute('viewBox', '0 0 512 512');
        couponSVG.innerHTML = '<path d="M190.5 68.8L225.3 128H224 152c-22.1 0-40-17.9-40-40s17.9-40 40-40h2.2c14.9 0 28.8 7.9 36.3 20.8zM64 88c0 14.4 3.5 28 9.6 40H32c-17.7 0-32 14.3-32 32v64c0 17.7 14.3 32 32 32H480c17.7 0 32-14.3 32-32V160c0-17.7-14.3-32-32-32H438.4c6.1-12 9.6-25.6 9.6-40c0-48.6-39.4-88-88-88h-2.2c-31.9 0-61.5 16.9-77.7 44.4L256 85.5l-24.1-41C215.7 16.9 186.1 0 154.2 0H152C103.4 0 64 39.4 64 88zm336 0c0 22.1-17.9 40-40 40H288h-1.3l34.8-59.2C329.1 55.9 342.9 48 357.8 48H360c22.1 0 40 17.9 40 40zM32 288V464c0 26.5 21.5 48 48 48H224V288H32zM288 512H432c26.5 0 48-21.5 48-48V288H288V512z"/>';

        // Tạo container cho nội dung của coupon
        var couponContentDiv = document.createElement('div');
        couponContentDiv.className = 'coupon-content';

        var couponTitle = document.createElement('div');
        couponTitle.className = 'title';
        couponTitle.textContent = coupon.title;
        var couponContent = document.createElement('div');
        couponContent.textContent = coupon.content;
        var couponExpiry = document.createElement('div');
        couponExpiry.className = 'expiry';
        couponExpiry.textContent = `Hạn sử dụng: ${coupon.beginDate} - ${coupon.endDate}`;

        couponContentDiv.appendChild(couponTitle);
        couponContentDiv.appendChild(couponContent);
        couponContentDiv.appendChild(couponExpiry);

        // Tạo nút radio
        var couponInput = document.createElement('input');
        couponInput.type = 'radio';
        couponInput.name = 'coupon';
        couponInput.id = 'coupon' + coupon.id;
        couponInput.value=coupon.id;

        // Đưa các phần tử vào trong container của mã giảm giá
        couponDiv.appendChild(couponSVG);        // Cột 1
        couponDiv.appendChild(couponContentDiv); // Cột 2
        couponDiv.appendChild(couponInput);      // Cột 3
        couponDiv.value= coupon.price;

        // Đưa container của mã giảm giá vào danh sách mã giảm giá
        couponsList.appendChild(couponDiv);
    });
}







closeBtn.addEventListener('click', function () {
        popup.style.display = 'none';
    });

    window.addEventListener('click', function (event) {
        if (event.target === popup) {
            popup.style.display = 'none';
        }
    });

    async function fetchCoupons() {
        try{
            const response = await fetch("http://localhost:8080/voucher/get-voucher", {
                method: 'GET',

            });
            if(!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }

            const data = await response.json();
            console.log(data);
            renderCoupons(data)


        }catch (error) {
            console.error('Error:', error);
        }
    }

function renderCoupons(coupons) {
    couponsList.innerHTML = ''; // Xóa các mã giảm giá trước đó

    coupons.forEach(function (coupon) {
        // Tạo container cho từng mã giảm giá
        var couponDiv = document.createElement('div');
        couponDiv.className = 'coupon';

        // Tạo đối tượng SVG ảnh
        var couponSVG = document.createElementNS('http://www.w3.org/2000/svg', 'svg');
        couponSVG.setAttribute('xmlns', 'http://www.w3.org/2000/svg');
        couponSVG.setAttribute('viewBox', '0 0 512 512');
        couponSVG.innerHTML = '<path d="M190.5 68.8L225.3 128H224 152c-22.1 0-40-17.9-40-40s17.9-40 40-40h2.2c14.9 0 28.8 7.9 36.3 20.8zM64 88c0 14.4 3.5 28 9.6 40H32c-17.7 0-32 14.3-32 32v64c0 17.7 14.3 32 32 32H480c17.7 0 32-14.3 32-32V160c0-17.7-14.3-32-32-32H438.4c6.1-12 9.6-25.6 9.6-40c0-48.6-39.4-88-88-88h-2.2c-31.9 0-61.5 16.9-77.7 44.4L256 85.5l-24.1-41C215.7 16.9 186.1 0 154.2 0H152C103.4 0 64 39.4 64 88zm336 0c0 22.1-17.9 40-40 40H288h-1.3l34.8-59.2C329.1 55.9 342.9 48 357.8 48H360c22.1 0 40 17.9 40 40zM32 288V464c0 26.5 21.5 48 48 48H224V288H32zM288 512H432c26.5 0 48-21.5 48-48V288H288V512z"/>';

        // Tạo container cho nội dung của coupon
        var couponContentDiv = document.createElement('div');
        couponContentDiv.className = 'coupon-content';

        var couponTitle = document.createElement('div');
        couponTitle.className = 'title';
        couponTitle.textContent = coupon.title;
        var couponContent = document.createElement('div');
        couponContent.textContent = coupon.content;
        var couponExpiry = document.createElement('div');
        couponExpiry.className = 'expiry';
        couponExpiry.textContent = `Hạn sử dụng: ${coupon.beginDate} - ${coupon.endDate}`;

        couponContentDiv.appendChild(couponTitle);
        couponContentDiv.appendChild(couponContent);
        couponContentDiv.appendChild(couponExpiry);

        // Tạo nút radio
        var couponInput = document.createElement('input');
        couponInput.type = 'radio';
        couponInput.name = 'coupon';
        couponInput.id = 'coupon' + coupon.id;
        couponInput.value=coupon.id;

        // Đưa các phần tử vào trong container của mã giảm giá
        couponDiv.appendChild(couponSVG);        // Cột 1
        couponDiv.appendChild(couponContentDiv); // Cột 2
        couponDiv.appendChild(couponInput);      // Cột 3
        couponDiv.value= coupon.price;

        // Đưa container của mã giảm giá vào danh sách mã giảm giá
        couponsList.appendChild(couponDiv);
    });
}





