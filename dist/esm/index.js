import { registerPlugin } from '@capacitor/core';
const TruvideoSdkImage = registerPlugin('TruvideoSdkImage', {
    web: () => import('./web').then((m) => new m.TruvideoSdkImageWeb()),
});
export * from './definitions';
export { TruvideoSdkImage };
//# sourceMappingURL=index.js.map