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

    // Duyệt qua mảng data đã được sắp xếp và thêm từng option vào select
    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.ProvinceID;
        option.textContent = item.ProvinceName;
        selector.appendChild(option);
    });
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

    // Xóa các option cũ trong select
    selectElement.innerHTML = '';
// Sắp xếp mảng data theo tên quận
    data.sort((a, b) => a.DistrictName.localeCompare(b.DistrictName));
    // Duyệt qua mảng data và thêm từng option vào select
    data.forEach(item => {
        const option = document.createElement('option');
        option.value = item.DistrictID;
        option.textContent = item.DistrictName;
        selectElement.appendChild(option);
    });
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


