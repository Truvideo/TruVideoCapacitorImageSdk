import { TruvideoSdkImage } from 'truvideo-capacitor-image-sdk';

window.testEcho = () => {
    const inputValue = document.getElementById("echoInput").value;
    TruvideoSdkImage.echo({ value: inputValue })
}
