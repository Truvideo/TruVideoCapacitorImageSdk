export interface TruvideoSdkImagePlugin {
    echo(options: {
        value: string;
    }): Promise<{
        value: string;
    }>;
    editImage(options: {
        inputPath: string;
        outputPath: string;
    }): Promise<{
        result: string;
    }>;
}
