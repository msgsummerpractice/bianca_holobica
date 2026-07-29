const fetchBtn = document.getElementById('fetch-dog-btn');
const dogImage = document.getElementById('dog-image');
const loadingText = document.getElementById('loading');
const errorText = document.getElementById('error');

async function getDogImage() {
    loadingText.style.display = 'block';
    errorText.style.display = 'none';
    dogImage.style.display = 'none';

    try{
        const response = await fetch('https://dog.ceo/api/breeds/image/random');
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        const data = await response.json();
        dogImage.src = data.message;
        dogImage.style.display = 'block';
    } catch (error) {
        console.error('Error fetching dog image:', error);
        errorText.style.display = 'block';
    } finally{
        loadingText.style.display = 'none';
    }
}

fetchBtn.addEventListener('click', getDogImage);

