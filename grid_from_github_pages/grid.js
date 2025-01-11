function getUrlParameters() {
    const params = new URLSearchParams(window.location.search);
    return {
        width: parseInt(params.get('width')) || 10,
        height: parseInt(params.get('height')) || 10,
        colors: (params.get('colors') || '').split(',').map(c => parseInt(c)),
        words: (params.get('words') || '').split(',').map(w => parseInt(w)),
    };
}

function createGrid() {
    const { width, height, colors, words } = getUrlParameters();
    const container = document.getElementById('grid-container');
    const cellSize = Math.min(window.innerWidth / width, window.innerHeight / height);

    // Fetch and parse the words file
    fetch('../src/main/resources/words/codenames_words.txt')
        .then(response => response.text())
        .then(data => {
            const wordList = data.split('\n').filter(word => word.trim());
            // Now wordList contains all words from the file
        })
        .catch(error => console.error('Error loading words:', error));

    container.style.gridTemplateColumns = `repeat(${width}, ${cellSize}px)`;
    
    for (let i = 0; i < height * width; i++) {
        const cell = document.createElement('div');
        cell.innerHTML = wordList[i] || '';
        cell.className = `grid-cell color-${colors[i] || 0}`;
        cell.style.width = `${cellSize}px`;
        cell.style.height = `${cellSize}px`;
        container.appendChild(cell);
    }
}

window.onload = createGrid;
window.onresize = createGrid;
