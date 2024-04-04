import { Fragment } from 'react';
import { X } from 'phosphor-react-native';

// Style import
import {
  Container,
  Title,
  Subtitle,
  Header
} from './styles';

import { Pressable } from 'react-native';

import Modal from "react-native-modal";

// Theme import
import theme from '@theme/index';

type ModalProps = {
  isVisible: boolean;
}

type CustomModalProps = {
  title?: string;
  subtitle?: string | null;
  children: React.ReactNode;
  onClose: () => void;
  modalProps: ModalProps;
};

export function CustomModal({ 
  onClose, 
  title, 
  subtitle,
  children,
  modalProps,
}: CustomModalProps) {
  return (
    <Modal {...modalProps}>
      <Container>
        <Header>
          <Pressable onPress={onClose}>
            <X color={theme.COLORS.GRAY_300} weight="bold" />
          </Pressable>
        </Header>

        {Boolean(title) && <Title>{title}</Title>}
        {Boolean(subtitle) && <Subtitle>{subtitle}</Subtitle>}

        {children}
      </Container>
    </Modal>
  );
}
