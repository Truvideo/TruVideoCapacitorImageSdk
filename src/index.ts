import { registerPlugin } from '@capacitor/core';

import type { TruvideoSdkImagePlugin } from './definitions';

const TruvideoSdkImage = registerPlugin<TruvideoSdkImagePlugin>('TruvideoSdkVideo');

export * from './definitions';
export { TruvideoSdkImage };
