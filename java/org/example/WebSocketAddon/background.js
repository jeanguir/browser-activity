let socket;

function connectWebSocket() {
  socket = new WebSocket('ws://localhost:8080');
  socket.onopen = function () {
    console.log('WebSocket is connected');
  };
  socket.onclose = function () {
    console.log('WebSocket is closed. Reconnecting...');
    setTimeout(connectWebSocket, 1000);
  };
  socket.onerror = function (error) {
    console.error('WebSocket Error: ', error);
    socket.close();
  };
}

connectWebSocket();

chrome.tabs.onActivated.addListener(activeInfo => {
  chrome.tabs.get(activeInfo.tabId, tab => {
    if (tab && tab.url) {
      const url = new URL(tab.url);
      const domain = url.hostname;
      if (socket && socket.readyState === WebSocket.OPEN) {
        socket.send(domain);
      }
    }
  });
});

chrome.tabs.onUpdated.addListener((tabId, changeInfo, tab) => {
  if (tab.active && changeInfo.url) {
    const url = new URL(changeInfo.url);
    const domain = url.hostname;
    if (socket && socket.readyState === WebSocket.OPEN) {
      socket.send(domain);
    }
  }
});
