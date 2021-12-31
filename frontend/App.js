import React from 'react';
import {SafeAreaView, StatusBar, StyleSheet, useColorScheme, Alert, BackHandler} from 'react-native';
import WebView from "react-native-webview";

const App = () => {
    const isDarkMode = useColorScheme() === 'dark';
    return (
        <>
            <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'}/>
            <SafeAreaView style={styles.root}>
                <WebView
                    source={{uri: 'http://localhost:3000'}}/>
            </SafeAreaView>
        </>);
};

const styles = StyleSheet.create({root: {flex: 1},});

export default App;

// const styles = StyleSheet.create({
//     container: {
//         flex: 1,
//         backgroundColor: '#fff',
//         alignItems: 'stretch', // 이부분 중요 이거 안써주면 WebView 에 width 값을 지정해야함..
//         justifyContent: 'center',
//         // paddingTop: getStatusBarHeight(true),
//     },
// });

