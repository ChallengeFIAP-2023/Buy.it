import styled from 'styled-components/native';

export const Container = styled.View`
  padding-top: 20px;

  width: 100%;
  height: 100%;

  background-color: ${({ theme }) => theme.COLORS.GRAY_700};
`;

export const AvatarWrapper = styled.View`
  margin-top: 30px;
  align-items: center;
  justify-content: center;
`;
