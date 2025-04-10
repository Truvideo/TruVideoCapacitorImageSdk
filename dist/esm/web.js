import { WebPlugin } from '@capacitor/core';
export class TruvideoSdkImageWeb extends WebPlugin {
    async echo(options) {
        console.log('ECHO', options);
        return options;
    }
    async editImage(options) {
        console.log('ECHO', options);
        return options;
    }
}
//# sourceMappingURL=web.js.map