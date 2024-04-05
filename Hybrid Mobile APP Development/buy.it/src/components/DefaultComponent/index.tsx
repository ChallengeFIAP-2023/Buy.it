import React from 'react';

// Component import
import { Header, Props as HeaderProps } from '../Header';
import { Highlight, Props as HighlightProps } from '../Highlight';
import { StatusBar, Props as StatusBarProps } from '../StatusBar';

// Styles import
import { Container } from './styles';

interface Props {
  statusBarProps?: StatusBarProps;
  headerProps?: HeaderProps;
  highlightProps?: HighlightProps;
  showHeader?: boolean;
}

export function DefaultComponent({
  statusBarProps,
  headerProps,
  highlightProps,
  showHeader = true,
}: Props) {
  return (
    <Container>
      {statusBarProps && <StatusBar {...statusBarProps} />}
      {showHeader && <Header {...headerProps} />}
      {highlightProps && <Highlight {...highlightProps} />}
    </Container>
  );
}
