export const storageObserver: any = {
  onstorage (name:string, callback: Function) {
    const wn: any = window;
    wn.onstorage = ({key, newValue}) => {
      if (key && newValue && key === name) callback(key, newValue);
    }
  },

  remove (key) {
    localStorage.removeItem(key);
  },

  get (key) {
    const getItem = localStorage.getItem(key);
    if (getItem) return JSON.parse(getItem);
  },

  set (key, dataset) {
    const getItemDataset = this.get(key);
    localStorage.setItem(key, JSON.stringify({
      ...getItemDataset,
      ...dataset
    }));
  }
}
