export interface ICategoryCreate {
    name: string;
    image: File|undefined;
    description: string;
}

export interface IUploadedFile {
    originFileObj: File
}