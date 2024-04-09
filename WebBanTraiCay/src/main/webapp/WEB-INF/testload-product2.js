function generateRandomInteger(N) {
  return Math.floor(Math.random() * N) + 1;
}

function generateRandomFloat(N) {
  return (Math.random() * N + 1.0).toFixed(1);
}

async function loadProducts() {
  const elements = document.querySelectorAll('.elementor-widget-container .elementor-loop-container.elementor-grid');
  const productsPromises = Array.from(elements).map(async (element) => {
    const url = element.querySelector('.elementor-widget-container a').getAttribute('href');
    console.log(url);
    const thumbnail = element.querySelector('.elementor-widget-container a img').getAttribute('src');
    try {
      await fetch(url, {
        method: 'GET'
      });
      return {
        thumbnail: thumbnail
      };
    } catch (error) {
      console.error('Error fetching data:', error);
      return null;
    }
  });

  const results = await Promise.all(productsPromises);
  console.log(results.filter(Boolean));
}

loadProducts();
